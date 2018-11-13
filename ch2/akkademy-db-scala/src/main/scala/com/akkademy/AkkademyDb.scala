package com.akkademy

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetRequest}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.HashMap

class AkkademyDb extends Actor {
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      //返回成功
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match {
        //存在值
        case Some(x) => sender() ! x
        //不存在值
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    case o => Status.Failure(new ClassNotFoundException)
  }
}

object Main extends App {
  //之前的代码没有读取配置文件
  val system = ActorSystem.create("akkademy", ConfigFactory.load("application.conf"))
  //将自己变成的Actor子类,通过Props类传入系统
  val helloActor = system.actorOf(Props[AkkademyDb], name = "akkademy-db")
}
