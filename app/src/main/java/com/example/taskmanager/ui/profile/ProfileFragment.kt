package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.databinding.FragmentProfileBinding
import java.io.ByteArrayOutputStream
import android.util.Base64
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileImage: CircleImageView
    private lateinit var avatarImageView: CircleImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileImage = binding.profileImage
        avatarImageView = binding.profileImage
        sharedPreferences = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = binding.etName
        profileImage = binding.profileImage

        val sharedPreference =
            requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        val savedName = sharedPreference.getString("Username", "")
        etName.setText(savedName)

        val imageString = sharedPreference.getString("Image", "")
        if (imageString != null && imageString.isNotEmpty()) {
            val byteArray = Base64.decode(imageString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            profileImage.setImageBitmap(bitmap)
        } else {
            // Обработайте случай, когда imageString равен null или пуст
        }


        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val editor = sharedPreference.edit()
                editor.putString("Username", s.toString())
                editor.apply()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        avatarImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == Activity.RESULT_OK && data != null) {

            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                val inputStream =
                    requireActivity().contentResolver.openInputStream(selectedImageUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val imageString = bitmapToBase64(bitmap)
                val editor = sharedPreferences.edit()
                editor.putString("Image", imageString)
                editor.apply()
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap?): String {
        if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
        return ""
    }
}