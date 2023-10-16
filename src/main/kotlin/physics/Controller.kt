@file:Suppress("unused")

package physics

import java.awt.event.*

class CustomMouseWheelListener(val model: Model) : MouseWheelListener {
    override fun mouseWheelMoved(p0: MouseWheelEvent?) {
        p0!!
        model.scale *= Math.pow(1.1, p0.preciseWheelRotation)
    }
}

class CustomMouseListener(val model: Model) : MouseMotionListener, MouseListener {
    private var prevX : Int? = null
    private var prevY : Int? = null
    override fun mouseDragged(p0: MouseEvent?) {
        println(p0)
        p0!!
        if(prevX == null || prevY == null) {
            prevX = p0.x
            prevY = p0.y
            return
        }

        val dx = p0.x - prevX!!
        val dy = p0.y - prevY!!
        model.center -= Vector(dx, dy, 0) * model.scale
        prevX = p0.x
        prevY = p0.y
    }

    override fun mouseMoved(p0: MouseEvent?) {
        p0!!
        prevX = p0.x
        prevY = p0.y
    }

    override fun mouseClicked(p0: MouseEvent?) {

    }

    override fun mousePressed(p0: MouseEvent?) {
        println(p0)
        p0!!
        prevX = p0.x
        prevY = p0.y
    }

    override fun mouseReleased(p0: MouseEvent?) {
        println(p0)
        prevX = null
        prevY = null
    }

    override fun mouseEntered(p0: MouseEvent?) {
        println(p0)
    }

    override fun mouseExited(p0: MouseEvent?) {
        println(p0)
    }
}