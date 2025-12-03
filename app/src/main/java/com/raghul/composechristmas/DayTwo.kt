package com.raghul.composechristmas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize().drawWithCache {
            val brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF00003C), Color.Black)
            )
            onDrawBehind {
                drawRect(brush)
            }
        })
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 50.dp,y = 200.dp)
            .size(13.dp).drawStar(color = Color.White.copy(alpha = 0.5f)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 100.dp,y = 50.dp)
            .size(13.dp).drawStar(color = Color.White))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(x = 100.dp,y = 50.dp)
            .size(10.dp).drawStar(color = Color.White))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(x = 10.dp,y = 20.dp)
            .size(13.dp).drawStar(color = Color.White.copy(alpha = 0.5f)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-20).dp,y = 100.dp)
            .size(12.dp).drawStar(color = Color.White))
        Box(modifier = Modifier.fillMaxSize().trunk())
        for (i in 7 downTo 0) {
            val verticalShift = 0.08f * i
            val widthFactor = 0.55f + (0.05f * i)
            val colorFactor = i * 10
            Box(
                modifier = Modifier
                    .jaggeredCone(
                        jaggeredOffset = 20f,
                        rightOffset = 0.5f + (widthFactor / 2),
                        bottomYPercent = 0.35f + verticalShift,
                        color = Color(1, 90 - colorFactor, 25),
                        topYPercent = 0.1f + verticalShift
                    )
            )
        }
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
    val height = size.height

    val topLeft = Offset(x = width * 0.45f, y = height * 0.4f)
    val topRight = Offset(x = width * 0.55f, y = height * 0.4f)
    val bottomLeft = Offset(x = width * 0.38f, y = height * 0.97f)
    val bottomRight = Offset(x = width * 0.62f, y = height * 0.97f)
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
            drawPath(roundedPath, color = Color(red = 100, green = 64, blue = 6))
    }

}

fun Modifier.drawStar(
    color: Color
): Modifier = this.drawWithCache {
    onDrawBehind {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2

        val top = Offset(x = centerX, y = 0f)
        val left = Offset(x = 0f, y = centerY)
        val bottom = Offset(x = centerX, y = height)
        val right = Offset(x = width, y = centerY)

        val magnet = Offset(centerX, centerY)

        val path = Path().apply {
            moveTo(top.x, top.y)
            quadraticTo(magnet.x,magnet.y, right.x, right.y)
            quadraticTo(magnet.x, magnet.y, bottom.x, bottom.y)
            quadraticTo(magnet.x, magnet.y, left.x, left.y)
            quadraticTo(magnet.x, magnet.y, top.x, top.y)
            close()
        }
        drawPath(path, color)
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