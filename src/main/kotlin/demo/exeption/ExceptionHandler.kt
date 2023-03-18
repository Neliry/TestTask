package demo.exeption

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private const val ERROR = "error"

@ControllerAdvice
class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errors: MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }

    @ExceptionHandler(InvalidDataException::class)
    fun handleInvalidDataException(exception: InvalidDataException): ResponseEntity<Map<String, String?>> {
        val error: Map<String, String?> = mapOf(Pair(ERROR, exception.message))
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<Map<String, String?>> {
        val error: Map<String, String?> = mapOf(Pair(ERROR, exception.message))
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(exception: ConflictException): ResponseEntity<Map<String, String?>> {
        val error: Map<String, String?> = mapOf(Pair(ERROR, exception.message))
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error)
    }
}
