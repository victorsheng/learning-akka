package com.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.messages.{GetRequest, SetRequest}
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        //tell
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }

      describe("no given SetRequest") {
        it("should no key/value in map") {
          val actorRef = TestActorRef(new AkkademyDb)
          //tell
          actorRef ! SetRequest("key", "value")

          val akkademyDb = actorRef.underlyingActor
          akkademyDb.map.get("key2") should equal(None)
        }
      }

      describe(" my own test") {
        it("a") {
          val actorRef = TestActorRef(new AkkademyDb)
          //tell
          actorRef ! SetRequest("key", "hehe")
          actorRef ! "UFO"

          actorRef ! GetRequest("key")

          actorRef ! GetRequest("key2")
        }
      }
    }
  }
}

