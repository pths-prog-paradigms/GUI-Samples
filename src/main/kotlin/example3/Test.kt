@file:Suppress("unused", "DuplicatedCode")

package example3

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.GridLayout
import java.awt.font.FontRenderContext
import java.awt.font.TextLayout
import java.awt.geom.Rectangle2D
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
        val (size, xShift, yShift) = computeSuitableSize(
            g,
            "Times New Roman",
            Font.BOLD,
            "Make me feel good",
            minSize * 2 / 7,
            minSize * 2 / 7,
        )
        g.font = Font("Times New Roman", Font.BOLD, size)
        g.drawString("Make me feel good", width / 2 + xShift, height / 2 + yShift)
        println(xShift)
        println(yShift)
        println(size)
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

fun computeSuitableSize(
    g: Graphics,
    fontName: String,
    fontStyle: Int,
    text: String,
    width: Int,
    height: Int,
): Triple<Int, Int, Int> {
    val context: FontRenderContext = (g as Graphics2D).fontRenderContext

    val innerWidth = (width * 0.8).toInt()
    val innerHeight = (height * 0.8).toInt()

    var lowSize = 1
    var highSize = 1000

    while (highSize > lowSize) {
        val middle = (lowSize + highSize) / 2
        val f = Font(fontName, fontStyle, middle)
        val bounds: Rectangle2D = TextLayout(text, f, context).bounds
        if (bounds.width >= innerWidth || bounds.height >= innerHeight) {
            highSize = middle - 1
        } else {
            lowSize = middle + 1
        }
    }

    val font = Font(fontName, fontStyle, highSize)
    val bounds: Rectangle2D = TextLayout(text, font, context).bounds
    val xPos = -(bounds.width.toInt() / 2)
    val yPos = -(bounds.height / 2).toInt()

    return Triple(highSize, xPos, yPos)
}
