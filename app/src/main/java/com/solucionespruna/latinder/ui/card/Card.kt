package com.solucionespruna.latinder.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.solucionespruna.latinder.R
import com.solucionespruna.latinder.domain.User
import com.solucionespruna.latinder.ui.theme.LaTinderTheme

@Composable
fun Card(modifier: Modifier, transitionModifier: Modifier, user: User, onLike: () -> Unit = {}) {
  Box(modifier) {
    Column(transitionModifier) {
      Text(
        text = user.name,
        Modifier
          .fillMaxWidth()
          .padding(8.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
      )
      AsyncImage(
        model = user.imageURL, contentDescription = user.name,
        modifier = Modifier
          .weight(1f)
          .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally),
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground))
      Row (
        Modifier
          .fillMaxWidth()
          .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = {}, Modifier.weight(1f)) {
          Text(text = "Dislike")
        }
        Button(onClick = {}, Modifier.weight(1f)) {
          Text(text = "Undo")
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
      Card(Modifier, Modifier, User("User Name", "https://randomuser.me"))
    }
  }
}