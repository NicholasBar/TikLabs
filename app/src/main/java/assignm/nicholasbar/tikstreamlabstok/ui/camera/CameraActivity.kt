package assignm.nicholasbar.tikstreamlabstok.ui.camera

import android.Manifest
import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsActivity
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import android.Manifest.permission
import android.annotation.SuppressLint
import com.tbruyelle.rxpermissions2.RxPermissions


/**
 * PlaceHolder Camera activity for uploading content
 */
class CameraActivity : TokLabsActivity(), SurfaceHolder.Callback, Camera.PictureCallback {

    val TAG = CameraActivity::class.java.simpleName

    private lateinit var viewModel: CameraViewModel

    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null
    private var surfaceView: SurfaceView? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)


        RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->
                    if (granted) {
                        setupSurfaceHolder()
                    } else {
                        Toast.makeText(this, R.string.permission_warning, Toast.LENGTH_LONG).show()
                        setViewVisibility(R.id.showPermissionMsg, View.VISIBLE)
                    }
                }

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CameraViewModel::class.java)


    }

    private fun setViewVisibility(id: Int, visibility: Int) {
        val view = findViewById<View>(id)
        view!!.visibility = visibility
    }

    private fun setupSurfaceHolder() {
        setViewVisibility(R.id.startBtn, View.VISIBLE)
        setViewVisibility(R.id.surfaceView, View.VISIBLE)

        surfaceHolder = surfaceView!!.holder
        surfaceHolder!!.addCallback(this)
        setBtnClick()
    }

    private fun setBtnClick() {
        startBtn?.setOnClickListener { captureImage() }
    }

    private fun captureImage() {
        if (camera != null) {
            camera!!.takePicture(null, null, this)
        }
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        startCamera()
    }

    private fun startCamera() {
        camera = Camera.open()
        camera!!.setDisplayOrientation(90)
        try {
            camera!!.setPreviewDisplay(surfaceHolder)
            camera!!.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        resetCamera()
    }

    private fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            // Return if preview surface does not exist
            return
        }

        // Stop if preview surface is already running.
        camera!!.stopPreview()
        try {
            // Set preview display
            camera!!.setPreviewDisplay(surfaceHolder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start the camera preview...
        camera!!.startPreview()
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        releaseCamera()
    }

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }

    override fun onPictureTaken(bytes: ByteArray, camera: Camera) {
        //saveImage(bytes)
        resetCamera()
    }

    private fun saveImage(bytes: ByteArray) {
        val outStream: FileOutputStream
        try {
            val fileName = "UploadTik_" + System.currentTimeMillis() + ".jpg"
            val file = File(Environment.getExternalStorageDirectory(), fileName)
            outStream = FileOutputStream(file)
            outStream.write(bytes)
            outStream.close()
            Toast.makeText(this, "Picture Saved: $fileName", Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}