package com.example.mohamed.railwaymangementsystem.utiltes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintOutput;
import com.example.mohamed.railwaymangementsystem.R;

/**
 * Created by mohamed on 2/18/2019.
 */

public class GlobalMethods {

    static private Toast toast;
    static private AlertDialog alertDialog;
    static private AlertDialog complaintDialog;

    public static boolean isEmpty(String input) {
        if (input.trim().length() > 0)
            return false;
        else
            return true;
    }

    public static void createToast(Context context, String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void createAlertDialog(Context context, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialogView = View.inflate(context, R.layout.alert_dialog_layout, null);
        builder.setView(dialogView);
        TextView alertTextView = dialogView.findViewById(R.id.alert_dialog_tv);
        alertTextView.setText(message);
        Button alertButton = dialogView.findViewById(R.id.alert_dialog_btn);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();

    }

    public static void createComplaintDialog(Context context, ComplaintOutput data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View alertView = View.inflate(context, R.layout.complaint_dialog, null);

        TextView messageTextView = alertView.findViewById(R.id.message_dialog_tv);
        TextView ticketTextView = alertView.findViewById(R.id.ticket_dialog_tv);
        TextView emailTextView = alertView.findViewById(R.id.email_dialog_tv);
        TextView trainNoTextView = alertView.findViewById(R.id.train_no_dialog_tv);
        Button okButton = alertView.findViewById(R.id.complaint_dialog_btn);
        messageTextView.setText(data.getMessage());
        ticketTextView.setText(data.getTicketId());
        emailTextView.setText(data.getEmail());
        trainNoTextView.setText(data.getTicketId());
        builder.setView(alertView);
        complaintDialog = builder.create();
        complaintDialog.show();


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintDialog.hide();

            }
        });

    }
}
