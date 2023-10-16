@file:Suppress("unused")

package physics

import java.awt.Color
import java.awt.Graphics
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class CustomCanvas(val model: Model) : JPanel() {
    override fun paint(graphics: Graphics) {
        graphics.color = Color.black
        graphics.fillRect(0, 0, width, height)
        graphics.translate(width / 2, height / 2)

        for (particle in model.particles) {
            val position = particle.position - model.center
            graphics.color = particle.color
            graphics.fillOval(
                (position.x / model.scale - particle.radius).toInt(),
                (position.y / model.scale - particle.radius).toInt(),
                particle.radius * 2,
                particle.radius * 2,
            )
        }
    }
}

fun createWindow(model: Model): JFrame {
    val frame = JFrame("PTHS physics simulation")
    frame.layout = GridLayout(1, 1)
    frame.add(CustomCanvas(model))
    frame.addMouseWheelListener(CustomMouseWheelListener(model))
    frame.addMouseMotionListener(CustomMouseListener(model))
    frame.addMouseListener(CustomMouseListener(model))
    frame.pack()
    frame.extendedState = frame.extendedState or JFrame.MAXIMIZED_BOTH
    frame.isVisible = true
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    return frame
}

fun main() {
    val model = Model(
        mutableListOf(
            Particle(
                1.989E30,
                0.0,
                10,
                Vector(),
                Vector(15000, -15000, 0.0),
                "Sun",
                Color.yellow,
                Vector()
            ),
            Particle(
                1.989E30,
                0.0,
                5,
                Vector(150000000000.0, 0.0, 0.0),
                Vector(0.0, 15000.0, 0.0),
                "Earth",
                Color(127, 127, 255),
                Vector()
            ),
            Particle(
                1.989E30,
                0.0,
                5,
                Vector(0.0, 150000000000.0, 0.0),
                Vector(-15000.0, 0.0, 0.0),
                "Earth",
                Color.red,
                Vector()
            ),
            Particle(
                1.989E30,
                0.0,
                5,
                Vector(-150000000000.0, 0.0, 0.0),
                Vector(0.0, -15000.0, 0.0),
                "Earth",
                Color(127, 127, 255),
                Vector()
            ),
            Particle(
                1.989E30,
                0.0,
                5,
                Vector(0.0, -150000000000.0, 0.0),
                Vector(15000.0, 0.0, 0.0),
                "Earth",
                Color.red,
                Vector()
            ),
        ),
        0.5E9,
        Vector()
    )
    val frame = createWindow(model)

    while (true) {
        Thread.sleep(1)
        model.step(60 * 60.0)
        frame.repaint()
    }
}