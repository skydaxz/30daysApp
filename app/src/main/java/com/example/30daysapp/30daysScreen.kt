package com.example.`30daysapp`

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.`30daysapp`.model.Day
import com.example.a30daysapp.R
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.`30daysapp`.ui.theme.Shapes
import com.example.`30daysapp`.ui.theme.Lora
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysItem(day: Day, modifier: Modifier = Modifier){
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .heightIn(min = 100.dp)
            .padding(dimensionResource(R.dimen.padding_minuscule)),
        shape = Shapes.small
    ){
        Column (modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
            ) {
                DayInfo(day.dayRes, day.topDescriptionRes, modifier = Modifier.weight(4f))
                Spacer(modifier = Modifier.weight(0.25f))
                DayItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if(expanded){
                ExtraDayInfo(botDesc = day.bottomDescriptionRes, dayIcon = day.imageRes)
            }
        }
    }
}
@Composable
fun DayIcon(@DrawableRes dayIcon: Int, modifier: Modifier = Modifier){
    Image(
        modifier = modifier
            .fillMaxSize()
            .size(200.dp)
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(shape = Shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dayIcon),
        contentDescription = null
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayInfo(@StringRes dayNum: Int, @StringRes topDesc: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(
            text = stringResource(dayNum),
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = Lora,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Text(
            text = stringResource(topDesc),
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = Lora,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun ExtraDayInfo(@StringRes botDesc: Int, @DrawableRes dayIcon: Int){
    DayIcon(dayIcon)
    Text(
        text = stringResource(botDesc),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small),
            bottom = dimensionResource(R.dimen.padding_small)
        )
    )
}
@Composable
private fun DayItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
                Image(
                    modifier = modifier
                        .size(60.dp)
                        .padding(dimensionResource(R.dimen.padding_small))
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.habits),
                    contentDescription = null
                )

            }

        },
        modifier = modifier
    )
}