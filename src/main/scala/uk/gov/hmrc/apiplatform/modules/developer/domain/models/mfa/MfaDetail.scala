/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apiplatform.modules.developer.domain.models.mfa

import java.time.Instant

sealed trait MfaDetail {
  val id: MfaId
  val name: String
  def createdOn: Instant
  def verified: Boolean
}

object MfaDetail {
  import play.api.libs.json._
  import uk.gov.hmrc.play.json.Union

  case class AuthenticatorAppMfa(
      id: MfaId,
      name: String,
      createdOn: Instant,
      verified: Boolean = false
    ) extends MfaDetail

  case class SmsMfa(
      id: MfaId = MfaId.random,
      name: String,
      createdOn: Instant,
      mobileNumber: String,
      verified: Boolean = false
    ) extends MfaDetail

  implicit val formatApp: OFormat[MfaDetail.AuthenticatorAppMfa] = Json.format[MfaDetail.AuthenticatorAppMfa]
  implicit val formatSms: OFormat[MfaDetail.SmsMfa]              = Json.format[MfaDetail.SmsMfa]

  implicit val mfaDetailsResponseFormat: OFormat[MfaDetail] = Union.from[MfaDetail]("role")
    .and[MfaDetail.AuthenticatorAppMfa](MfaType.AUTHENTICATOR_APP.toString)
    .and[MfaDetail.SmsMfa](MfaType.SMS.toString)
    .format
}
