package com.akkademy

import akka.actor.Actor

class RevertString extends Actor {


  override def receive: Receive = {
    case body: String => println(s"receive ${body}")
      val reverse = new StringBuffer(body).reverse.toString
      sender() ! reverse
    case _ => println("unkown message")
  }
}
