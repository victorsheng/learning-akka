package com.akkademy

import akka.actor.Actor

/**
  * 解析文章
  */
class ParsingActor extends Actor {
  override def receive: Receive = {
    case ParseHtmlArticle(key, html) =>
      sender() ! ArticleBody(key, de.l3s.boilerpipe.extractors.ArticleExtractor.INSTANCE.getText(html))
    case x =>
      println("unknown message " + x.getClass)
  }
}
