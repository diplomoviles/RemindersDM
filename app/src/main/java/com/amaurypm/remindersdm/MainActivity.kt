package com.amaurypm.remindersdm


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setupReminder()


    }

    private fun setupReminder() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Establecer la alarma para que se repita todos los días a la misma hora
        /*val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 9) // Hora en formato de 24 horas
            set(Calendar.MINUTE, 21)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )*/

        // Establecer la alarma para que se repita todos los días a la misma hora y día de la semana específico
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY) // Día específico (Sábado en este ejemplo)
            set(Calendar.HOUR_OF_DAY, 9) // Hora en formato de 24 horas
            set(Calendar.MINUTE, 30)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY * 7, // intervalo semanal
            pendingIntent
        )

        /*val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.YEAR, 2023) // Año específico
            set(Calendar.MONTH, Calendar.NOVEMBER) // Mes específico (Enero es 0)
            set(Calendar.DAY_OF_MONTH, 2) // Día específico
            set(Calendar.HOUR_OF_DAY, 9) // Hora en formato de 24 horas
            set(Calendar.MINUTE, 44)
            set(Calendar.SECOND, 0)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                // Manejar caso donde la aplicación no tiene permisos para programar alarmas exactas
                // Podemos mostrar un mensaje al usuario o tomar otra acción apropiada
                return
            }
        }

        // Utilizar setExactAndAllowWhileIdle (es para mejorar la privacidad)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )*/
    }

}