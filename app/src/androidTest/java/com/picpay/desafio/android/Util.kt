package com.picpay.desafio.android

import androidx.test.platform.app.InstrumentationRegistry

fun executeCommands(commands: List<String>) {
    commands.forEach { command ->
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(command)
    }
}