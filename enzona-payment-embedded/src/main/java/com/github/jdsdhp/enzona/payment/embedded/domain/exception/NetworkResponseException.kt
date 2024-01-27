package com.github.jdsdhp.enzona.payment.embedded.domain.exception

/**
 * Creates an exception based on the given error code.
 * @param errorCode The error code.
 * @return The corresponding exception based on the error code.
 */
internal fun createExceptionByErrorCode(errorCode: Int?): Exception = when (errorCode) {
    400 -> BadRequestException()
    401 -> UnauthorizedException()
    402 -> PaymentRequiredException()
    403 -> ForbiddenException()
    404 -> NotFoundException()
    405 -> MethodNotAllowedException()
    406 -> NotAcceptableException()
    407 -> ProxyAuthenticationRequiredException()
    408 -> RequestTimeoutException()
    409 -> ConflictException()
    410 -> GoneException()
    411 -> LengthRequiredException()
    412 -> PreconditionFailedException()
    413 -> PayloadTooLargeException()
    414 -> URITooLongException()
    415 -> UnsupportedMediaTypeException()
    416 -> RangeNotSatisfiableException()
    417 -> ExpectationFailedException()
    418 -> ImATeapotException()
    421 -> MisdirectedRequestException()
    422 -> UnprocessableEntityException()
    423 -> LockedException()
    424 -> FailedDependencyException()
    426 -> UpgradeRequiredException()
    428 -> PreconditionRequiredException()
    429 -> TooManyRequestsException()
    431 -> RequestHeaderFieldsTooLargeException()
    451 -> UnavailableForLegalReasonsException()
    500 -> InternalServerErrorException()
    501 -> NotImplementedException()
    502 -> BadGatewayException()
    503 -> ServiceUnavailableException()
    504 -> GatewayTimeoutException()
    505 -> HTTPVersionNotSupportedException()
    506 -> VariantAlsoNegotiatesException()
    507 -> InsufficientStorageException()
    508 -> LoopDetectedException()
    510 -> NotExtendedException()
    511 -> NetworkAuthenticationRequiredException()
    else -> NetworkResponseException()
}

open class NetworkResponseException(
    val errorMessage: String = "Unknown network error",
    val errorCode: Int = DEFAULT_ERROR_CODE,
) : Exception(errorMessage) {

    companion object {
        const val DEFAULT_ERROR_CODE = -1
    }

    fun isDefaultNetworkError(): Boolean = errorCode == DEFAULT_ERROR_CODE

}

class BadRequestException : NetworkResponseException("Bad Request", 400)

class UnauthorizedException : NetworkResponseException("Unauthorized", 401)

class PaymentRequiredException : NetworkResponseException("Payment Required", 402)

class ForbiddenException : NetworkResponseException("Forbidden", 403)

class NotFoundException : NetworkResponseException("Not Found", 404)

class MethodNotAllowedException : NetworkResponseException("Method Not Allowed", 405)

class NotAcceptableException : NetworkResponseException("Not Acceptable", 406)

class ProxyAuthenticationRequiredException :
    NetworkResponseException("Proxy Authentication Required", 407)

class RequestTimeoutException : NetworkResponseException("Request Timeout", 408)

class ConflictException : NetworkResponseException("Conflict", 409)

class GoneException : NetworkResponseException("Gone", 410)

class LengthRequiredException : NetworkResponseException("Length Required", 411)

class PreconditionFailedException : NetworkResponseException("Precondition Failed", 412)

class PayloadTooLargeException : NetworkResponseException("Payload Too Large", 413)

class URITooLongException : NetworkResponseException("URI Too Long", 414)

class UnsupportedMediaTypeException : NetworkResponseException("Unsupported Media Type", 415)

class RangeNotSatisfiableException : NetworkResponseException("Range Not Satisfiable", 416)

class ExpectationFailedException : NetworkResponseException("Expectation Failed", 417)

class ImATeapotException : NetworkResponseException("I'm a Teapot", 418)

class MisdirectedRequestException : NetworkResponseException("Misdirected Request", 421)

class UnprocessableEntityException : NetworkResponseException("Unprocessable Entity", 422)

class LockedException : NetworkResponseException("Locked", 423)

class FailedDependencyException : NetworkResponseException("Failed Dependency", 424)

class UpgradeRequiredException : NetworkResponseException("Upgrade Required", 426)

class PreconditionRequiredException : NetworkResponseException("Precondition Required", 428)

class TooManyRequestsException : NetworkResponseException("Too Many Requests", 429)

class RequestHeaderFieldsTooLargeException :
    NetworkResponseException("Request Header Fields Too Large", 431)

class UnavailableForLegalReasonsException :
    NetworkResponseException("Unavailable For Legal Reasons", 451)

class InternalServerErrorException : NetworkResponseException("Internal Server Error", 500)

class NotImplementedException : NetworkResponseException("Not Implemented", 501)

class BadGatewayException : NetworkResponseException("Bad Gateway", 502)

class ServiceUnavailableException : NetworkResponseException("Service Unavailable", 503)

class GatewayTimeoutException : NetworkResponseException("Gateway Timeout", 504)

class HTTPVersionNotSupportedException : NetworkResponseException("HTTP Version Not Supported", 505)

class VariantAlsoNegotiatesException : NetworkResponseException("Variant Also Negotiates", 506)

class InsufficientStorageException : NetworkResponseException("Insufficient Storage", 507)

class LoopDetectedException : NetworkResponseException("Loop Detected", 508)

class NotExtendedException : NetworkResponseException("Not Extended", 510)

class NetworkAuthenticationRequiredException :
    NetworkResponseException("Network Authentication Required", 511)