/**
  *
  */
package org.quant4s.rest

import spray.http.{HttpEntity, MediaTypes}

/**
  *
  */
class DataServiceSpec  extends RoutingSpec with DataService{
  implicit def actorRefFactory = system

  "数据管理," - {
    " 提交数据订阅请求" in {
      Post("/data/000001.XSHE,BAR,5", HttpEntity(MediaTypes.`application/json`,
        """000001.XSHE,BAR,5"""
      )) ~> dataServiceRoute ~> check {
        responseAs[String] shouldEqual """{"code":0}"""
        // TODO 检测到ZEROMQ 有数据发送
      }
      Post("/data/000001.XSHE,TICK",HttpEntity(MediaTypes.`application/json`,
        """000001.XSHE,TICK"""
        )) ~> dataServiceRoute ~> check {
        responseAs[String] shouldEqual """{"code":0}"""
      }
      Post("/data/000001.XSHE,MACD,5,9|9|21", HttpEntity(MediaTypes.`application/json`,
        """000001.XSHE,MACD,5,9|9|21"""
      )) ~> dataServiceRoute ~> check {
        responseAs[String] shouldEqual """{"code":0}"""
      }

    }

  }

}
