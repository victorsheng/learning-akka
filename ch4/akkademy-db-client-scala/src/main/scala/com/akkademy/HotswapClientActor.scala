package com.akkademy

import akka.actor.{Actor, Stash}
import com.akkademy.messages.{Connected, Request}

/**
 * Use mailbox with stash-capacity
 * or build some sort of timeout to avoid memory leak.
 */

class HotswapClientActor(address: String) extends Actor with Stash {
  private val remoteDb = context.system.actorSelection(address)


  //离线处理
  override def receive = {
    case x: Request =>  //can't handle until we know remote system is responding
      remoteDb ! new Connected //see if the remote actor is up
      stash() //stash message for later
    case _: Connected => // Okay to start processing messages.
      unstashAll()
      context.become(online)
  }

  //在线处理
  def online: Receive = {
    case x: Disconnected =>
      context.unbecome()
    case x: Request =>
      //本地转发
      remoteDb forward x //forward is used to preserve sender
  }
}

/**
 * Disconnect msg is unimplemented in this example.
 * Because we're not dealing w/ sockets directly,
 * we could instead implement an occasional ping/heartbeat that restarts
 * this Actor if the remote actor isn't responding.
 * After restarting, the actor will revert to the
 * default state and stash messages
 */

class Disconnected