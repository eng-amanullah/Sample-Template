package com.amanullah.sampletemplate.feature.charactersview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterView(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    url: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.4F)
                    .padding(4.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .aspectRatio(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    model = imageUrl,
                    contentDescription = "",
                    modifier = Modifier.padding(4.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5F)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textModifier = Modifier.fillMaxWidth()

                Text(
                    text = name, modifier = textModifier,
                    fontSize = 18.sp,
                    /*fontWeight = FontWeightBold,
                    fontFamily = FontBold*/
                )

                Text(
                    text = url,
                    fontSize = 12.sp,
                    modifier = textModifier,
                    textDecoration = TextDecoration.Underline,
                    /*fontWeight = FontWeightRegular,
                    fontFamily = FontRegular*/
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardUIPreview() {
    Surface(
        Modifier.background(Color.Gray)
    ) {
        CharacterView(
            modifier = Modifier, name = "Amanullah Sarker", "", "HUMAN",
        )
    }
}