package com.example.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class todo extends AppCompatActivity {
    RecyclerView tasksRecyclerView;
    FloatingActionButton fab;
    DatabaseHelper db;
    List<Task> taskList;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        tasksRecyclerView = findViewById(R.id.tasksrecyclerview);
        fab = findViewById(R.id.fab);
        db = new DatabaseHelper(this);
        taskList = new ArrayList<>();

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList, this);
        tasksRecyclerView.setAdapter(taskAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(todo.this, AddNewTaskActivity1.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    public void editTask(int id, String name) {
        Intent intent = new Intent(todo.this, AddNewTaskActivity1.class);
        intent.putExtra("task_id", id);
        intent.putExtra("task_name", name);
        startActivity(intent);
    }

    private void loadTasks() {
        taskList.clear();  // Clear the current list to avoid duplication
        Cursor cursor = db.getAllTasks();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                taskList.add(new Task(cursor.getInt(0), cursor.getString(1)));
            }
        }
        taskAdapter.notifyDataSetChanged();
    }
}
