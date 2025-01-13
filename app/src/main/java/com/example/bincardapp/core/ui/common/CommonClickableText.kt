package com.example.bincardapp.core.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import com.example.bincardapp.core.ui.theme.LightBlue

@Composable
fun CommonClickableText(
    text: String,
    clickableText: String,
    interactionListener: () -> Unit,
) {
    Text(
        text = buildAnnotatedString {
            append("$text: ")
            withLink(
                LinkAnnotation.Clickable(
                    tag = text,
                    styles = TextLinkStyles(
                        SpanStyle(
                            color = LightBlue,
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = { interactionListener() }
                )
            ) {
                append(clickableText)
            }
        }
    )
}