package com.flashpool.ui.util

import java.util.Locale

fun formatInrPrice(amount: Double): String =
    "₹${"%,.2f".format(Locale("en", "IN"), amount)}"
