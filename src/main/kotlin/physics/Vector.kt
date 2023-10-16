package physics

import kotlin.math.sqrt

data class Vector(val x: Double, val y: Double, val z: Double) {
    constructor(vararg elements: Number) : this(
        (elements.getOrNull(0) ?: 0).toDouble(),
        (elements.getOrNull(1) ?: 0).toDouble(),
        (elements.getOrNull(2) ?: 0).toDouble(),
    )


    override fun toString(): String = "($x, $y, $z)"

    operator fun plus(v: Vector) = Vector(x + v.x, y + v.y, z + v.z)
    operator fun minus(v: Vector) = Vector(x - v.x, y - v.y, z - v.z)
    operator fun unaryMinus() = Vector(-x, -y, -z)
    operator fun times(k: Number) = Vector(x * k.toDouble(), y * k.toDouble(), z * k.toDouble())
    operator fun div(k: Number) = this * (1 / k.toDouble())
    infix fun dot(v: Vector) = x * v.x + y * v.y + z * v.z
    infix fun x(v: Vector) = Vector(
        y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x
    )
    val squared get() = this dot this
    val magnitude get() = sqrt(this dot this)
    val orth get() = this / magnitude

    // Assuming coeff = 1/z_0
    fun projected(coeff: Number): Pair<Double, Double> {
        val coeff = coeff.toDouble()
        return (x / (1 - coeff * z) to (y / (1 - coeff * z)))
    }
}

operator fun Number.times(v: Vector) = v * this
