@file:Suppress("unused", "DuplicatedCode")

package example2

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class CustomCanvas : JPanel() {
    override fun paint(g: Graphics) {
        g.color = Color.black
        g.fillRect(0, 0, width, height)

        g.color = Color.red
        val minSize = minOf(width, height)
        g.fillOval(width / 2 - minSize / 5, height / 2 - minSize / 5, minSize * 2 / 5, minSize * 2 / 5)

        g.color = Color.black
        g.font = Font("Times New Roman", Font.BOLD, 24)
        g.drawString("Make me feel good", width / 2 - 111, height / 2 - 12)
    }
}

fun main() {
    val frame = JFrame()
    frame.layout = GridLayout(1, 1)
    frame.add(CustomCanvas())
    frame.pack()
    frame.extendedState = frame.extendedState or JFrame.MAXIMIZED_BOTH
    frame.isVisible = true
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}

