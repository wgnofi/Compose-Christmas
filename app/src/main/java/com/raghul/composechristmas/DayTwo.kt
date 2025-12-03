package com.raghul.composechristmas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.raghul.composechristmas.ui.theme.ComposeChristmasTheme

@Composable
fun Tree() {
    Box(modifier = Modifier.trunk())
    for (i in 5 downTo 0) {
        val verticalShift = 0.08f * i
        val widthFactor = 0.6f + (0.05f * i)
        val colorFactor = i * 10
        Box(
            modifier = Modifier
                .jaggeredCone(jaggeredOffset = 20f, rightOffset = 0.5f + (widthFactor / 2), bottomYPercent = 0.35f + verticalShift,
                    color = Color(1, 100 - colorFactor ,25),
                    topYPercent = 0.1f + verticalShift)
        )
    }
}

@Composable
fun Modifier.jaggeredCone(
    jaggeredOffset: Float,
    rightOffset: Float,
    topYPercent: Float,
    bottomYPercent: Float,
    color: Color
): Modifier = this.drawWithCache {
        val width = size.width
        val height = size.height

        val top = Offset(x = width /2 ,y = height * topYPercent )
        val bottomRight = Offset(x = width * rightOffset, y= height * bottomYPercent)
        val bottomLeft = Offset(x = width * (1 - rightOffset), y = height * bottomYPercent)

        val vertices = mutableListOf<Float>()

        vertices.add(top.x)
        vertices.add(top.y)

        vertices.add(bottomRight.x)
        vertices.add(bottomRight.y)

        val steps = 10

        val dx = (bottomLeft.x - bottomRight.x) / steps
        val dy = (bottomLeft.y - bottomRight.y) / steps
        for (i in 1 until steps) {
            val baseX = bottomRight.x + i * dx
            val baseY = bottomRight.y + i * dy

            val jagOffset = if (i % 2 == 0) -jaggeredOffset else +jaggeredOffset

            vertices.add(baseX)
            vertices.add(baseY + jagOffset)
        }

        vertices.add(bottomLeft.x)
        vertices.add(bottomLeft.y)

        val roundedPolygon = RoundedPolygon(vertices = vertices.toFloatArray(),
            rounding = CornerRounding(radius = 30f, smoothing = 0.5f))
        val roundedPath = roundedPolygon.toPath().asComposePath()
        onDrawBehind {
            drawPath(roundedPath, color = color)
        }
    }.fillMaxSize()

@Composable
fun Modifier.trunk(

): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height / 1

    val topLeft = Offset(x = width * 0.45f, y = height * 0.4f)
    val topRight = Offset(x = width * 0.55f, y = height * 0.4f)
    val bottomLeft = Offset(x = width * 0.35f, y = height * 0.8f)
    val bottomRight = Offset(x = width * 0.65f, y = height * 0.8f)
    val vertices = mutableListOf<Float>()
    vertices.add(topLeft.x)
    vertices.add(topLeft.y)
    vertices.add(topRight.x)
    vertices.add(topRight.y)
    vertices.add(bottomRight.x)
    vertices.add(bottomRight.y)
    vertices.add(bottomLeft.x)
    vertices.add(bottomLeft.y)
    val roundedPolygon = RoundedPolygon(vertices = vertices.toFloatArray(),
        rounding = CornerRounding(radius = 20f, smoothing = 0.5f))
    val roundedPath = roundedPolygon.toPath().asComposePath()
    onDrawBehind {
        translate(top = 200f) {
            drawPath(roundedPath, color = Color(red = 110, green = 84, blue = 56))
        }
    }

}

@Preview
@Composable
private fun TreePreview() {
    ComposeChristmasTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Tree()
        }
    }
}