package com.raghul.composechristmas

import android.graphics.BlurMaskFilter
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import com.raghul.composechristmas.ui.theme.ComposeChristmasTheme
import kotlin.math.PI
import kotlin.math.atan2


val textFont = FontFamily(
    Font(R.font.moc_bold, FontWeight.ExtraBold)
)
@Composable
fun Tree() {
    val iftOne = rememberInfiniteTransition(label = "star_transition_one")
    val iftTwo = rememberInfiniteTransition(label = "star_transition_two")
    val iftThree = rememberInfiniteTransition(label = "star_transition_three")
    val iftFour = rememberInfiniteTransition(label = "star_transition_four")
    val iftFive = rememberInfiniteTransition(label = "star_transition_five")
    val iftSix = rememberInfiniteTransition(label = "star_transition_six")
    val iftSeven = rememberInfiniteTransition(label = "star_transition_seven")
    var colorsF by remember {
        mutableStateOf(false)
    }
    val pairOne = Color(250, 10, 0) to Color(0xFF00FF00)
    val pairTwo = Color(50, 200, 249) to Color(252,115,92)
    val starTopperScale = rememberInfiniteTransition(label = "star_topper_scale")
    val topperScale by starTopperScale.animateFloat(
        initialValue = 125f,
        targetValue = 130f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "star_topper_scale"
    )

    val yOffsetForSnowFlake = rememberInfiniteTransition(label = "y_offset_for_snowflake")
    val ySnowFlake by yOffsetForSnowFlake.animateFloat(
        initialValue = -50f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(7000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "y_offset_for_snowflake"
    )
    val alphaSnowFlake by yOffsetForSnowFlake.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha_for_snowflake"
    )
    val rotationSnowFlake by yOffsetForSnowFlake.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(7000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart),
        label = "rotation_for_snowflake"
    )

    val fairyOne = rememberInfiniteTransition(label = "fairyOne")
    val fairyTwo = rememberInfiniteTransition(label = "fairyTwo")
    val fairyAnimOne by fairyOne.animateColor(
        initialValue = if (colorsF) pairOne.first.copy(alpha = 0.5f) else pairTwo.first.copy(alpha = 0.5f),
        targetValue = if (colorsF) pairOne.first else pairTwo.first,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "fairyOne_color"
    )

    val fairyAnimTwo by fairyTwo.animateColor(
        initialValue =  if (colorsF) pairOne.second else pairOne.second,
        targetValue =  if (colorsF) pairOne.second.copy(alpha = 0.2f) else pairTwo.second.copy(alpha = 0.2f),
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "fairyTwo_color"
    )
    val textGradient = Brush.linearGradient(
        colors = listOf(Color(214, 0, 28),
            Color(214, 100, 28))
    )
    val textMeasurer = rememberTextMeasurer()
    val alphaOne by iftOne.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftOne_opacity"
    )

    val alphaTwo by iftTwo.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftTwo_opacity"
    )

    val alphaThree by iftThree.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftThree_opacity"
    )

    val alphaFour by iftFour.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftFour_opacity"
    )

    val alphaFive by iftFive.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftFive_opacity"
    )

    val alphaSix by iftSix.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftSix_opacity"
    )

    val alphaSeven by iftSeven.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iftSeven_opacity"
    )

    val roseBase = Color(183, 110, 121)
    val lightPink = Color(255, 182, 193)
    val highlight = Color(255, 240, 245)
    val gold = Color(255,215, 0)
    val metalGold = Color(211, 175, 55)
    val oldGold = Color(208, 182, 61)
    val listZeroBauble = listOf(gold, metalGold, oldGold, oldGold, metalGold, gold)
    val listOneBauble = listOf(roseBase, lightPink, highlight, lightPink, roseBase)
    val deepBlue = Color(0, 102, 153)
    val vibrantCyan = Color(0, 153, 204)
    val electricAqua = Color(102, 255, 255)
    val listTwoBauble = listOf(deepBlue, vibrantCyan, electricAqua, vibrantCyan, deepBlue)
    val darkGreen = Color(1, 48, 33)
    val kellyGreen = Color(75, 181, 67)
    val brightLime = Color(176, 255, 80)
    val listThreeBauble = listOf(brightLime ,darkGreen, kellyGreen, brightLime, kellyGreen,brightLime)

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF00003C), Color(0, 0, 10))
                )
                onDrawBehind {
                    drawRect(brush)
                }
            }
            .clickable(
                onClick = {
                    colorsF = !colorsF
                }
            ))
        Box(modifier = Modifier
            .fillMaxSize()
            .trunk())
        Box(modifier = Modifier
            .fillMaxSize()
            .snowGround())
        Box(modifier = Modifier
            .fillMaxSize()
            .align(alignment = Alignment.BottomCenter)
            .shadow()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 50.dp, y = 200.dp)
            .size(13.dp)
            .drawStar(color = Color.White.copy(alpha = alphaOne)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 100.dp, y = 50.dp)
            .size(13.dp)
            .drawStar(color = Color(0xFFFFFFE0).copy(alphaTwo)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(x = 20.dp, y = 10.dp)
            .size(10.dp)
            .drawStar(color = Color.White.copy(alpha = alphaThree)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(x = 100.dp, y = 50.dp)
            .size(10.dp)
            .drawStar(color = Color.White.copy(alphaFour)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 10.dp, y = 120.dp)
            .size(12.dp)
            .drawStar(color = Color.White.copy(alpha = alphaFive)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-10).dp, y = 220.dp)
            .size(12.dp)
            .drawStar(color = Color.White.copy(alpha = alphaSix)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-20).dp, y = 100.dp)
            .size(12.dp)
            .drawStar(color = Color.White.copy(alphaSeven)))
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-20).dp, y = 500.dp + ySnowFlake.dp)
            .size(20.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = (20).dp, y = 400.dp + ySnowFlake.dp)
            .size(30.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-20).dp, y = 400.dp + ySnowFlake.dp)
            .size(12.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = (30).dp, y = 600.dp + ySnowFlake.dp)
            .size(20.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = (30).dp, y = 720.dp + ySnowFlake.dp)
            .size(12.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        Box(modifier = Modifier
            .align(alignment = Alignment.TopEnd)
            .offset(x = (-30).dp, y = 760.dp + ySnowFlake.dp)
            .size(15.dp)
            .alpha(alphaSnowFlake)
            .rotate(rotationSnowFlake)
            .snowFlake())
        for (i in 6 downTo 0) {
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
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 70.dp, y = (-120).dp)
                .size(30.dp)
                .baubles(ballColors = listThreeBauble, tipColor = Color(0, 34, 23))
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(y = 60.dp)
            .size(105.dp)
            .radialStar()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(y = 50.dp)
            .size(topperScale.dp)
            .alpha(0.3f)
            .radialStar()
        )

        Box(modifier = Modifier
            .size(180.dp)
            .align(alignment = Alignment.Center)
            .offset(x = (-5).dp, y = (-190).dp)
            .fairyLights(
                fairyAnimOne,
                fairyAnimTwo
            ))
        Box(modifier = Modifier
            .size(205.dp)
            .align(alignment = Alignment.Center)
            .offset(x = (-5).dp, y = (-140).dp)
            .tinselGarland()
        )
        Box(modifier = Modifier
            .size(250.dp)
            .align(alignment = Alignment.Center)
            .tinselGarland())
        Box(modifier = Modifier
            .size(280.dp)
            .align(alignment = Alignment.Center)
            .offset(y = 120.dp)
            .tinselGarland())
        Box(modifier = Modifier
            .size(300.dp)
            .align(alignment = Alignment.Center)
            .offset(y = 240.dp)
            .tinselGarland())
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-70).dp, y = (280).dp)
                .size(30.dp)
                .baubles(ballColors = listZeroBauble, tipColor = Color(148, 114, 20))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-60).dp, y = (-50).dp)
                .size(30.dp)
                .baubles(ballColors = listZeroBauble, tipColor = Color(148, 114, 20))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-5).dp, y = 20.dp)
                .size(30.dp)
                .baubles(ballColors = listTwoBauble, tipColor = Color(0, 71, 107))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 140.dp, y = 220.dp)
                .size(30.dp)
                .baubles(ballColors = listOneBauble, tipColor = Color(128, 77, 85))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 60.dp, y = 90.dp)
                .size(30.dp)
                .baubles(ballColors = listOneBauble, tipColor = Color(128, 77, 85))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-70).dp, y = 150.dp)
                .size(30.dp)
                .baubles(ballColors = listTwoBauble, tipColor = Color(0, 71, 107))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 30.dp, y = 220.dp)
                .size(30.dp)
                .baubles(ballColors = listThreeBauble, tipColor = Color(0, 34, 23))
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .offset(x = 20.dp, y = 10.dp)
            .size(60.dp)
            .moon())
        Box(modifier = Modifier
            .align(alignment = Alignment.Center)
            .size(60.dp)
            .offset(x = 20.dp, y = (-70).dp)
            .candyCane()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.Center)
            .size(60.dp)
            .rotate(-5f)
            .offset(x = (-50).dp, y = (60).dp)
            .candyCane()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.Center)
            .size(60.dp)
            .rotate(5f)
            .offset(x = (50).dp, y = (160).dp)
            .candyCane()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.Center)
            .size(75.dp)
            .rotate(2f)
            .offset(x = (50).dp, y = (350).dp)
            .ribbonBox()
        )
        Box(modifier = Modifier
            .align(alignment = Alignment.Center)
            .size(55.dp)
            .rotate(-2f)
            .offset(x = (-20).dp, y = (350).dp)
            .ribbonBox()
        )
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .offset(x = (120).dp, y = (815).dp)
        ) {
            drawText(
                textMeasurer = textMeasurer,
                text = "Merry Christmas",
                style = TextStyle(
                    brush = textGradient,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = textFont
                )
            )
        }
        Box(modifier = Modifier
            .align(alignment = Alignment.BottomStart)
            .size(120.dp)
            .snowMan()
        )
    }
}

@Composable
fun Modifier.ribbonBox(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val topLeft = Offset(x = width * 0.2f, y = height * 0.2f)
    val offTop = Offset(x = width * 0.5f, y = height * 0.1f)
    val offTop2 = Offset(x = width * 0.4f, y = height * 0.1f)
    val offTop3 = Offset(x = width * 0.15f, y = height * 0.2f)
    onDrawBehind {
        drawRoundRect(color = Color.Red, topLeft = topLeft, size = Size(width = width * 0.6f, height = height * 0.6f),
            cornerRadius = CornerRadius(x = 20f, y = 20f))
        rotate(-20f) {
            drawRoundRect(
                color = Color.Red,
                topLeft = offTop,
                size = Size(width = width * 0.1f, height = height * 0.2f),
                cornerRadius = CornerRadius(x = 10f, y = 10f)
            )
        }
        rotate(20f) {
            drawRoundRect(color = Color.Red, topLeft = offTop2, size = Size(width = width * 0.1f, height = height * 0.2f),
                cornerRadius = CornerRadius(x = 10f, y = 10f))
        }
        drawRoundRect(color =Color(208, 224, 249), topLeft = offTop3, size = Size(width = width * 0.7f, height = height * 0.2f),
            cornerRadius = CornerRadius(x = 20f, y = 20f))
    }
}
@Composable
fun Modifier.snowMan(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val bigCircleRadOffset = Offset(x = width * 0.5f, y = height * 0.7f)
    val medCircleRadOffset = Offset(x = width * 0.5f, y = height * 0.7f)
    val leftEye = Offset(x = width * 0.45f, y = height * 0.3f)
    val rightEye = Offset(x = width * 0.6f, y = height * 0.3f)
    val scarfLeft = Offset(x = width * 0.35f, y = height * 0.48f)
    val scarfRight = Offset(x = width * 0.65f, y = height * 0.48f)
    val scarfMagnet = Offset(x = width * 0.5f, y = height * 0.5f)
    val leftHandStart = Offset(x = width * 0.1f, y = height * 0.5f)
    val leftHandEnd = Offset(x = width * 0.4f, y = height * 0.6f)
    val noseFirst = Offset(x = width * 0.52f, y = height * 0.3f)
    val noseSecond = Offset(x = width * 0.65f, y = height * 0.35f)
    val noseThird = Offset(x = width * 0.5f, y = height * 0.35f)
    val nosePath = Path().apply {
        moveTo(noseFirst.x, noseFirst.y)
        lineTo(noseSecond.x, noseSecond.y)
        lineTo(noseThird.x, noseThird.y)
    }
    val lPath = Path().apply {
        moveTo(leftHandStart.x, leftHandStart.y)
        lineTo(leftHandEnd.x, leftHandEnd.y)
    }
    val rightHandStart = Offset(x = width * 0.6f, y = height * 0.6f)
    val rightHandEnd = Offset(x = width * 0.9f, y = height * 0.5f)
    val branchOneStart = Offset(x = width * 0.2f, y = height * 0.4f)
    val branchOneEnd = Offset(x = width * 0.25f, y = height * 0.55f)
    val branchTwoStart = Offset(x = width * 0.8f, y = height * 0.55f)
    val branchTwoEnd = Offset(x = width * 0.9f, y = height * 0.6f)
    val bOnePath = Path().apply {
        moveTo(branchOneStart.x, branchOneStart.y)
        lineTo(branchOneEnd.x, branchOneEnd.y)
    }
    val bTwoPath = Path().apply {
        moveTo(branchTwoStart.x, branchTwoStart.y)
        lineTo(branchTwoEnd.x, branchTwoEnd.y)
    }
    val rPath = Path().apply {
        moveTo(rightHandEnd.x, rightHandEnd.y)
        lineTo(rightHandStart.x, rightHandStart.y)
    }
    val scarfPath = Path().apply {
        moveTo(scarfLeft.x, scarfLeft.y)
        quadraticTo(scarfMagnet.x, scarfMagnet.y, scarfRight.x, scarfRight.y)
    }
    val mcrTwo = Offset(x = width * 0.5f, y = height * 0.8f)
    val head = Offset(x = width * 0.5f, y = height * 0.35f)
    onDrawBehind {
        drawPath(bOnePath, color = Color(101, 67, 33), style = Stroke(width = width * 0.03f))
        drawPath(bTwoPath, color = Color(101, 67, 33), style = Stroke(width = width * 0.03f))
        drawPath(lPath, color = Color(101, 67, 33), style = Stroke(width = width * 0.05f))
        drawPath(rPath, color = Color(101, 67, 33), style = Stroke(width = width * 0.05f))
        drawCircle(color = Color.White, radius = width * 0.25f, center = bigCircleRadOffset)
        drawCircle(color = Color.White, radius = width * 0.17f, center = head)
        drawCircle(color = Color.Black, radius = width * 0.03f, center = medCircleRadOffset)
        drawCircle(color = Color.Black, radius = width * 0.03f, center = mcrTwo)
        drawCircle(color = Color.Black, radius = width * 0.02f, center = leftEye)
        drawCircle(color = Color.Black, radius = width * 0.02f, center = rightEye)
        drawPath(nosePath, color = Color(255,140,0), style = Fill)
        drawPath(scarfPath, color = Color.Red, style = Stroke(width = width * 0.05f))
    }
}

@Composable
fun Modifier.tinselGarland(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val strokeWidth = width * 0.1f
    val capWidth = strokeWidth / 2
    val leftMost = Offset(x = 0f + capWidth, y = height * 0.5f)
    val rightMost = Offset(x = width - capWidth, y = height * 0.3f)
    val magnet = Offset(x = width * 0.7f, y = height * 0.5f)
    val path = Path().apply {
        moveTo(leftMost.x, leftMost.y)
        quadraticTo(magnet.x, magnet.y, rightMost.x, rightMost.y)
    }
    onDrawBehind {
        drawPath(path, color = Color.White,
            style = Stroke(width = width * 0.1f, cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 25f), 0f))
        )
    }
}
//
//@Preview
//@Composable
//private fun GarlandPreview() {
//    ComposeChristmasTheme(
//
//    ) {
//        Box(modifier = Modifier.size(200.dp).tinselGarland())
//    }
//}

@Composable
fun Modifier.snowGround():Modifier  = this.drawWithCache {
    val pureWhite = Color(255, 255, 255)
    val snowWhite = Color(255, 250, 250)
    val whitesmoke = Color(245, 245, 245)
    val azureishWhite = Color(208, 224, 249)

    onDrawBehind {
            val width = size.width
            val height = size.height

            val bottomLeft = Offset(x = 0F, y = height)
            val bottomRight = Offset(x = width, y = height)

            val magnet = Offset(x = width / 2, y = height * 0.85f)
            val path = Path().apply {
                moveTo(bottomLeft.x, bottomLeft.y)
                quadraticTo(magnet.x, magnet.y, bottomRight.x, bottomRight.y)
                lineTo(bottomLeft.x, bottomLeft.y)
                close()
            }
                drawPath(path, brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0.0f to snowWhite,
                        0.1f to whitesmoke,
                        0.4f to azureishWhite,
                        0.6f to azureishWhite,
                        0.9f to whitesmoke,
                        1.0f to pureWhite
                    )
                ))

    }
}


@Composable
fun Modifier.shadow(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height

    val bottomLeft = Offset(x = 0F, y = height)
    val bottomRight = Offset(x = width, y = height)

    val magnet = Offset(x = width / 2, y = height * 0.85f)
    val path = Path().apply {
        moveTo(bottomLeft.x, bottomLeft.y)
        quadraticTo(magnet.x, magnet.y, bottomRight.x, bottomRight.y)
        lineTo(bottomLeft.x, bottomLeft.y)
        close()
    }
    val paint = Paint().apply {
        color = Color.Black.copy(alpha = 0.6f)
        blendMode = BlendMode.SrcOver
        asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(60f, BlurMaskFilter.Blur.NORMAL)
        }
    }

    onDrawBehind {
        clipPath(path) {
            drawIntoCanvas { canvas ->
                canvas.drawRoundRect(
                    left = width * 0.2f,
                    top = height * 0.9f,
                    right = width * 0.98f,
                    bottom = height * 0.98f,
                    radiusX = 20f,
                    radiusY = 20f,
                    paint = paint
                )
            }
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
): Modifier = this
    .drawWithCache {
        val width = size.width
        val height = size.height

        val top = Offset(x = width / 2, y = height * topYPercent)
        val bottomRight = Offset(x = width * rightOffset, y = height * bottomYPercent)
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

        val roundedPolygon = RoundedPolygon(
            vertices = vertices.toFloatArray(),
            rounding = CornerRounding(radius = 30f, smoothing = 0.5f)
        )
        val roundedPath = roundedPolygon.toPath().asComposePath()
        onDrawBehind {
            drawPath(roundedPath, color)
        }
    }
    .fillMaxSize()

@Composable
fun Modifier.trunk(

): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height

    val topLeft = Offset(x = width * 0.45f, y = height * 0.4f)
    val topRight = Offset(x = width * 0.55f, y = height * 0.4f)
    val bottomLeft = Offset(x = width * 0.38f, y = height * 0.95f)
    val bottomRight = Offset(x = width * 0.62f, y = height * 0.95f)
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

@Composable
fun Modifier.fairyLights(
    colorOne: Color,
    colorTwo: Color
): Modifier = this.drawWithCache {

    val width = size.width
    val height = size.height
    val control = Offset(x = width * 0.6f, y = height * 0.3f)
    val path = Path().apply {
        moveTo(x = width * 0.1f, height * 0.5f)
        quadraticTo(control.x, control.y, width * 0.9f, height * 0.35f)
    }
    val pathMeasure = PathMeasure()
    pathMeasure.setPath(path, false)
    val totalLength = pathMeasure.length
    val interval = size.minDimension * 0.1f
    val exLen = size.minDimension * 0.05f
    onDrawBehind {
        drawPath(path, color = Color.Black, style = Stroke(width = width * 0.01f))

        var distance = 0f
        var count = 0
        while (distance <= totalLength) {
            val innerPath = pathMeasure.getPosition(distance)
            drawLine(color = Color.Black, start = Offset(innerPath.x, innerPath.y),
                end = Offset(innerPath.x, innerPath.y + exLen), strokeWidth = size.minDimension * 0.01f)
            val colorForBulb =if (count % 2 == 0) colorOne else colorTwo
            drawOval(color = colorForBulb, topLeft = Offset(innerPath.x - (size.minDimension * 0.015f), innerPath.y + exLen), size = Size(width = size.minDimension * 0.03f, height = size.minDimension * 0.04f))
            drawOval(color = colorForBulb.copy(alpha = 0.1f),
                topLeft = Offset(innerPath.x - (size.minDimension * 0.03f), innerPath.y +  (size.minDimension * 0.03f)),
                size = Size(width = size.minDimension * 0.06f, height = size.minDimension * 0.08f))
            distance += interval
            count++
        }
    }
}

@Composable
fun Modifier.baubles(
    ballColors: List<Color>,
    tipColor: Color
    ): Modifier = this.drawWithCache {
    onDrawBehind {
        drawRect(color = tipColor, topLeft =  Offset(x = size.width * 0.475f, y = size.height * 0.03f), size = Size(width = size.width * 0.04f, height = size.height * 0.2f))
        translate(top = size.height / 7) {
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(x = size.width * 0.415f, y = size.height * 0.05f),
                size = size.div(6f),
                cornerRadius = CornerRadius(x = 20f, y = 20f)
            )
            drawCircle(brush = Brush.sweepGradient(colors = ballColors), radius = size.maxDimension / 3)
        }
    }
}
@Composable
fun Modifier.radialStar(

): Modifier = this.drawWithCache {
    val starPolygon = RoundedPolygon.star(
        numVerticesPerRadius = 5,
        innerRadius = size.minDimension / 5,
        centerX = size.width / 2,
        centerY = size.height / 2,
        radius = size.minDimension / 2
    )
    val starPath = starPolygon.toPath().asComposePath()
    onDrawBehind {
        rotate(degrees = -90f) {
            drawPath(
                starPath,
                brush = Brush.radialGradient(colors = listOf(Color.White, Color(255, 205, 0)))
            )
        }
    }
}

@Composable
fun Modifier.candyCane(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val left = Offset(x = width * 0.3f, height * 0.5f)

    val path = Path().apply {
        moveTo(x = width * 0.4f, y = height * 0.9f)
        arcTo(rect = Rect(center = Offset(x = width * 0.5f, y = height * 0.3f), radius = size.minDimension / 6),
            startAngleDegrees = 180f, sweepAngleDegrees = 180f, forceMoveTo = false)
    }
    onDrawBehind {
        drawContext.canvas.saveLayer(size.toRect(), Paint())
        drawPath(
            path, color = Color.White,
            style = Stroke(width * 0.1f, cap = StrokeCap.Round)
        )
        rotate(60f) {
            drawRect(
                color = Color.Red,
                topLeft = left,
                size = Size(width = width * 0.1f, height = height * 0.2f),
                blendMode = BlendMode.SrcIn
            )
            drawRect(
                color = Color.Red,
                topLeft = Offset(left.x, left.y * 0.2f),
                size = Size(width = width * 0.1f, height = height * 0.3f),
                blendMode = BlendMode.SrcIn
            )
            drawRect(
                color = Color.Red,
                topLeft = Offset(left.x * 1.6f, left.y),
                size = Size(width = width * 0.1f, height = height * 0.3f),
                blendMode = BlendMode.SrcIn
            )
            drawRect(
                color = Color.Red,
                topLeft = Offset(left.x * 2.2f, left.y * 1.2f),
                size = Size(width = width * 0.1f, height = height * 0.3f),
                blendMode = BlendMode.SrcIn
            )
        }
        drawContext.canvas.restore()
    }
}
@Composable
fun Modifier.stockings(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val sockTopLeft = Offset(x = width * 0.35f, y = height * 0.2f)
    val sockBottomRight = Offset(x = width * 0.6f, y = height * 0.7f)
    val sockBottomLeft = Offset(x = width * 0.8f, y = height * 0.95f)
    val controlPointLeft = Offset(x = width * 0.1f, height * 1.1f)
    val sockControl = Offset(x = width * 1.1f, y = height * 0.8f)

    val sockTopRight = Offset(x = width * 0.6f, y = height * 0.2f)
    val sockControlToTop = Offset(x = width * 0.5f, y = height * 0.4f)

    val stockPath = Path().apply {
        moveTo(sockTopLeft.x, sockTopLeft.y)
        quadraticTo(controlPointLeft.x, controlPointLeft.y, sockBottomLeft.x, sockBottomLeft.y)
        quadraticTo(sockControl.x, sockControl.y, sockBottomRight.x, sockBottomRight.y)
        quadraticTo(sockControlToTop.x, sockControlToTop.y, sockTopRight.x, sockTopRight.y)
    }

    onDrawBehind {
        clipPath(stockPath) {
            drawPath(stockPath, color = Color.Red)
            rotate(degrees = -10f) {
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x = width * 0.2f, y = height * 0.4f),
                    size = Size(width = width * 0.4f, height = height * 0.05f)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x = width * 0.2f, y = height * 0.6f),
                    size = Size(width = width * 0.4f, height = height * 0.05f)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x = width * 0.2f, y = height * 0.8f),
                    size = Size(width = width * 0.7f, height = height * 0.05f)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x = width * 0.2f, y = height * 0.956f),
                    size = Size(width = width * 0.7f, height = height * 0.05f)
                )
            }
        }
        drawRoundRect(color = Color.White, topLeft = Offset(x = width * 0.23f, y = height * 0.2f), size = Size(width = width * 0.4f, height = height * 0.15f),
            cornerRadius = CornerRadius(x = 20f, y = 20f))
    }

}
@Composable
fun Modifier.moon(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val center = Offset(width / 2, height / 2)
    val moonRadius = size.minDimension / 2

    val craterPaint = Paint().apply {
        color = Color(0xFFDDDDDD)
        blendMode = BlendMode.SrcOver
        asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
        }
    }

    val moonPath = Path().apply {
        addOval(Rect(center = center, radius = moonRadius))
    }
    onDrawBehind {
        clipPath(moonPath) {
            drawCircle(color = Color.White, radius = moonRadius, center)

            drawIntoCanvas { canvas ->
                canvas.drawCircle(
                    center = center + Offset(-width * 0.2f, -height * 0.1f),
                    radius = moonRadius * 0.25f,
                    craterPaint
                )
                canvas.drawOval(rect = Rect(center, moonRadius / 5 ), craterPaint)
                canvas.drawCircle(
                    center = center + Offset(width * 0.4f, height * 0.2f),
                    radius = moonRadius * 0.3f,
                    craterPaint
                )
                canvas.drawCircle(
                    center = center + Offset(width * 0.2f, -height * 0.2f),
                    radius = moonRadius * 0.5f,
                    craterPaint
                )
                canvas.drawCircle(
                    center = center + Offset(-width * 0.2f, height * 0.3f),
                    radius = moonRadius * 0.2f,
                    craterPaint
                )
            }
        }
    }
}

@Composable
fun Modifier.snowFlake(): Modifier = this.drawWithCache {
    val width = size.width
    val height = size.height
    val lineOneStart = Offset(x = width * 0.2f, y = height * 0.3f)
    val lineOneEnd = Offset(x = width * 0.8f, y = height * 0.7f)
    val lineTwoStart = Offset(x = width * 0.8f, y = height * 0.3f)
    val lineTwoEnd = Offset(x = width * 0.2f, y = height * 0.7f)
    val lineThreeStart = Offset(x = width * 0.5f, y = height * 0.15f)
    val lineThreeEnd = Offset(x = width * 0.5f, y = height * 0.85f)
    val lineOne = Path().apply {
        moveTo(lineOneStart.x, lineOneStart.y)
        lineTo(lineOneEnd.x, lineOneEnd.y)
    }
    val lineTwo = Path().apply {
        moveTo(lineTwoStart.x, lineTwoStart.y)
        lineTo(lineTwoEnd.x, lineTwoEnd.y)
    }
    val lineThree = Path().apply {
        moveTo(lineThreeStart.x, lineThreeStart.y)
        lineTo(lineThreeEnd.x, lineThreeEnd.y)
    }
    val pathMeasureFirst = PathMeasure()
    pathMeasureFirst.setPath(lineOne, false)
    onDrawBehind {
        val gap = size.minDimension * 0.1f
        val strokeWidth = width * 0.05f
        listOf(lineOne, lineTwo, lineThree).forEach { path ->
            drawPath(path, color = Color.White, style = Stroke(width = strokeWidth * 1.5f, cap = StrokeCap.Round))

            val pm = PathMeasure()
            pm.setPath(path, false)
            val len = pm.length
            fun drawV(fraction: Float, invertAngles: Boolean) {
                val pos = pm.getPosition(len * fraction)
                val tan = pm.getTangent(len * fraction)
                val angle = (atan2(tan.y.toDouble(), tan.x.toDouble()) * 180 / PI).toFloat()
                val firstOffset = if (invertAngles) -45f else 45f
                val secondOffset = if (invertAngles) 45f else -45f

                rotate(angle + firstOffset, pivot = pos) {
                    drawLine(Color.White, start = pos.copy(y = pos.y + gap), end = pos, strokeWidth = strokeWidth, cap = StrokeCap.Round)
                }
                rotate(angle + secondOffset, pivot = pos) {
                    drawLine(Color.White, start = pos.copy(y = pos.y - gap), end = pos, strokeWidth = strokeWidth, cap = StrokeCap.Round)
                }
            }

            drawV(0.2f, false)
            drawV(0.8f, true)
        }
    }

}

@Preview
@Composable
private fun StarPreview() {
    ComposeChristmasTheme {
        Box(modifier = Modifier
            .size(200.dp)
            .snowMan())
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