package com.solucionespruna.latinder.ui.card

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

sealed class CardAction {
  data object Like : CardAction()
  data object Dislike : CardAction()
  data object Undo : CardAction()
}

@Composable
fun CardsScreen() {
  var cardState: CardAction by remember { mutableStateOf(CardAction.Undo) }
  val translationX = remember { Animatable(0f) }
  val coroutineScope = rememberCoroutineScope()
  val draggableState = rememberDraggableState(onDelta = { delta ->
    coroutineScope.launch {
      translationX.snapTo(translationX.value + delta)
    }
  })
  val boxBackgroundColor = when (translationX.value) {
    0f -> MaterialTheme.colorScheme.background
    else -> Color.Transparent
  }
  val boxModifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)
    .padding(8.dp)
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.toFloat();
  val lerpFraction = min(1f, max(0f, translationX.value) / screenWidth)
  Box(
    Modifier
      .background(Color.White)
      .padding(8.dp)) {
    Card(boxModifier, Modifier, text = "This is the bottom card")
    Card(
      modifier = Modifier
        .fillMaxSize()
        .background(boxBackgroundColor)
        .padding(8.dp)
      ,
      transitionModifier = Modifier
        .graphicsLayer {
          this.translationX = translationX.value
          this.translationY = -translationX.value
          this.rotationZ = lerp(0f, 25f, lerpFraction)
          this.scaleY = lerp(1f, 0.5f, lerpFraction)
          this.scaleX = lerp(1f, 0.5f, lerpFraction)
        }
        .draggable(draggableState, orientation = Orientation.Horizontal,
          onDragStopped = {
            coroutineScope.launch {
              cardState = if (translationX.value > screenWidth / 2) {
                translationX.animateTo(screenWidth)
                CardAction.Like
              } else if (translationX.value < -screenWidth / 2) {
                translationX.animateTo(-screenWidth)
                CardAction.Dislike
              } else {
                translationX.animateTo(0f)
                CardAction.Undo
              }
            }
          })
        .background(androidx.compose.ui.graphics.lerp(Color.Transparent, Color.Green, lerpFraction)),
      text = "This is the top card") {
      cardState = CardAction.Like
    }
  }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
  return (1 - fraction) * start + fraction * stop
}

@Composable
@Preview(
  showBackground = true,
  showSystemUi = true,
  uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
fun CardScreenPreview() {
  LaTinderTheme {
    Surface {
      CardsScreen()
    }
  }
}
