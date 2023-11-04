package com.amaurypm.remindersdm

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Creado por Amaury Perea Matsumura el 03/11/23
 */
class AlarmReceiver : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            createNotificationChannel(context)
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        val intent = Intent(context, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder =NotificationCompat.Builder(context, context.getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle("¡Recordatorio urgente!")
            .setContentText("Tienes una tarea pendiente a revisar - Amaury")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) //Para se cancele al hacer click
            .setSound(soundUri)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Manejar si es que tengo que pedir permiso para el POST_NOTIFICATIONS
            return
        }
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())

    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MyChannelName"
            val descriptionText = "My channel description"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(
                context.getString(R.string.default_notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
}