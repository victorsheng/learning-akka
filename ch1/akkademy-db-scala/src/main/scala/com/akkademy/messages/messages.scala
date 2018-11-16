package com.akkademy.messages

case class SetRequest(key: String, value: String)

case class GetRequest(key: String)
