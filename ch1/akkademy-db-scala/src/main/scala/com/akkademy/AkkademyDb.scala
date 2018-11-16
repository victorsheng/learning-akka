package com.akkademy

import akka.actor.Actor
import akka.event.Logging
import com.akkademy.messages.{GetRequest, SetRequest}

import scala.collection.mutable.HashMap

class AkkademyDb extends Actor {
  //成员变量
  val map = new HashMap[String, String]
  //日志
  val log = Logging(context.system, this)

  /**
    * 接收消息
    *
    * @return
    */
  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case GetRequest(key) => {
      log.info("received SetRequest - key: {}", key)
      //    sender(); 如何取出来?
      val value = map.get(key)
      log.info(s"get value: $value")
    }
    case o => log.info("received unknown message: {}", o);
  }
}
