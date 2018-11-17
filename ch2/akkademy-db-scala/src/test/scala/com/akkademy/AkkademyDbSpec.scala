package com.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }

  describe("revert") {
    describe("normal request") {
      it("abcdef") {
        val actorRef = TestActorRef(new com.akkademy.RevertString)
        val future = actorRef ? "abcdef"
        future onComplete {
          case body => body should equal("fedcba")
        }
      }
      it(" int not string "){
        val actorRef = TestActorRef(new com.akkademy.RevertString)
        val future = actorRef ? 123
        future onComplete {
          case body => body should equal("unkown message")
        }
      }
    }
  }


}

