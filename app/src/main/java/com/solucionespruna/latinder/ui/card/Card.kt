package com.solucionespruna.latinder.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.latinder.R
import com.solucionespruna.latinder.ui.theme.LaTinderTheme

@Composable
fun Card(modifier: Modifier, transitionModifier: Modifier, text: String, onLike: () -> Unit = {}) {
  Box(modifier) {
    Column(transitionModifier) {
      Text(text = text,
        Modifier
          .fillMaxWidth()
          .padding(8.dp), textAlign = TextAlign.Center)
      Image(
        painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Card Image",
        Modifier
          .weight(1f)
          .align(alignment = Alignment.CenterHorizontally))
      Row (
        Modifier
          .fillMaxWidth()
          .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = {}, Modifier.weight(1f)) {
          Text(text = "Dislike")
        }
        Button(onClick = {}, Modifier.weight(1f)) {
          Text(text = "undo")
        }
        Button(onClick = onLike, Modifier.weight(1f)) {
          Text(text = "Like")
        }
      }
    }
  }
}

@Composable
@Preview(
  showBackground = true,
  showSystemUi = true,
  uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
fun CardPreview() {
  LaTinderTheme {
    Surface {
      Card(Modifier, Modifier,"Test Text")
    }
  }
}