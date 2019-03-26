package com.example.countershockkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImagePickerDialogFragment: DialogFragment(), ImagePickerAdapter.Callback {
    lateinit var preferences:SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    lateinit var adapter: ImagePickerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = context!!.getSharedPreferences(ShockUtils.SHOCK_SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_media_picker, container, false)

        val items = ImageStorer(context!!).getAllImages()

        adapter = ImagePickerAdapter(items, this)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager

        return view
    }

    override fun itemSelected(item: ImageModel) {
        editor.putInt(getString(R.string.key_photo_id), item.id)
        editor.commit()
        dismiss()

        LocalBroadcastManager.getInstance(context!!).
            sendBroadcast(Intent(ShockUtils.MEDIA_UPDATED_ACTION))
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window?.setLayout(width, height)
    }

}