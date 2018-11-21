package com.akkademy

import akka.actor.{ActorSystem, Props}
import akka.cluster.client.ClusterClientReceptionist
import akka.routing.BalancingPool

object Main extends App {
  val system = ActorSystem("Akkademy")
  val clusterController = system.actorOf(Props[ClusterController], "clusterController")

  val workers = system.actorOf(BalancingPool(5).props(Props[ArticleParseActor]), "workers")

  ClusterClientReceptionist(system).registerService(workers)
}

