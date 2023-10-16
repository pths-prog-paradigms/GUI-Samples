@file:Suppress("unused")

package physics

import java.awt.Color

data class Particle(
    val mass: Double,
    val charge: Double,
    val radius: Int,
    var position: Vector,
    var velocity: Vector,
    val name: String?,
    var color: Color,
    var acceleration: Vector,
)


const val G = 6.67E-11
const val k = 9E9

class Model(
    val particles: MutableList<Particle>,
    var scale: Double,
    var center: Vector,
) {
    fun step(time: Double) {
        for (i in 0 until particles.size) {
            var force = Vector()
            for (j in 0 until particles.size) {
                if (i == j) continue
                val r = (particles[i].position - particles[j].position)
                val gravity = -G * particles[i].mass * particles[j].mass / r.squared * r.orth
                val electro = k * particles[i].charge * particles[j].charge / r.squared * r.orth
                force += gravity + electro
            }
            particles[i].acceleration = force / particles[i].mass
        }
        for (i in 0 until particles.size) {
            particles[i].velocity += particles[i].acceleration * time
            particles[i].position += particles[i].velocity * time
        }
    }
}