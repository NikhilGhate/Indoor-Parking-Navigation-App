package com.example.GUI;

import android.graphics.Canvas;

public interface VariableChangeListener {
	void onVariableChanged(int cellX, Canvas can, float[] assoc_arr);
}
