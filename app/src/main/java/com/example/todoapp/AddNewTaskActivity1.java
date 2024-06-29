package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewTaskActivity1 extends AppCompatActivity {
    EditText newTaskText;
    Button newTaskButton;
    DatabaseHelper db;
    int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task1);

        newTaskText = findViewById(R.id.newtaskText);
        newTaskButton = findViewById(R.id.newTaskButton);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("task_id")) {
            taskId = intent.getIntExtra("task_id", -1);
            newTaskText.setText(intent.getStringExtra("task_name"));
            newTaskButton.setText("Update");
        }

        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = newTaskText.getText().toString();
                if (!task.isEmpty()) {
                    if (taskId == -1) {
                        if (db.addTask(task)) {
                            Toast.makeText(AddNewTaskActivity1.this, "Task added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddNewTaskActivity1.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (db.updateTask(taskId, task)) {
                            Toast.makeText(AddNewTaskActivity1.this, "Task updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddNewTaskActivity1.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                        }
                    }
                    finish();
                } else {
                    Toast.makeText(AddNewTaskActivity1.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
