@file:Suppress("unused")

package example1

import java.awt.GridLayout
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame

class CustomWindowListener : WindowListener {
    override fun windowOpened(p0: WindowEvent?) {
    }

    override fun windowClosing(p0: WindowEvent?) {
        p0!!
        create()
        p0.window.dispose()
    }

    override fun windowClosed(p0: WindowEvent?) {
    }

    override fun windowIconified(p0: WindowEvent?) {
    }

    override fun windowDeiconified(p0: WindowEvent?) {
    }

    override fun windowActivated(p0: WindowEvent?) {
    }

    override fun windowDeactivated(p0: WindowEvent?) {
    }
}

fun create() {
    val frame = JFrame()
    frame.layout = GridLayout(1, 1)
    for (i in 1..4) frame.add(JButton("$i"))
    frame.pack()
    frame.extendedState = frame.extendedState or JFrame.MAXIMIZED_BOTH
    frame.isVisible = true
    frame.addWindowListener(CustomWindowListener())
}

fun main() {
    create()
}