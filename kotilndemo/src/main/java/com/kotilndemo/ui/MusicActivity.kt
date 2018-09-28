package com.kotilndemo.ui

import adapter.MusicAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CheckResult
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.SeekBar
import android.widget.Toast
import com.kotilndemo.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_misic_list.view.*
import kotlinx.android.synthetic.main.music_activity.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MusicActivity : AppCompatActivity() {
    private var dialog: Dialog? = null
    private var musicNameList: ArrayList<String> = ArrayList()
    private var musicList: ArrayList<Int> = ArrayList()
    private var mediaPlayer = MediaPlayer()
    private var localPosition: Int = 0
    private var hasMusic: Boolean = false //判断是否有歌曲在播放
    private var animator: ObjectAnimator? = null
    private var animatorAxle: RotateAnimation? = null
    private var animatorAxle2: RotateAnimation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_activity)
        initMusicData()
        initView()
        initDialog()


       var  m = MediaPlayer()
        Log.i("current_time",""+m.currentPosition)
    }


    /**
     * 初始化音乐目录
     */
    private fun initMusicData() {

        musicNameList.add("茉莉花-阿里郎")
        musicNameList.add("再次爱上你-阿里郎")
        musicNameList.add("兰花指-阿里郎")
        musicNameList.add("醉赤壁-林俊杰")
        musicNameList.add("成全-林宥嘉")
        musicNameList.add("笨小孩-刘德华")
        musicNameList.add("假装-刘德华")
        musicNameList.add("开心的马骝-刘德华")

        musicList.add(R.raw.molihua)
        musicList.add(R.raw.zaiciaishangni)
        musicList.add(R.raw.lanhuazhi)
        musicList.add(R.raw.zuichibi)
        musicList.add(R.raw.chengquan)
        musicList.add(R.raw.benxiaohai)
        musicList.add(R.raw.jiazhuang)
        musicList.add(R.raw.kaixindemaliu)
    }


    private fun initView() {
        misic_list.setOnClickListener({
            dialog!!.show()
        })

        up_bt.setOnClickListener({
            initMedia((localPosition - 1))
        })

        next_bt.setOnClickListener({
            initMedia((localPosition + 1))
        })

        play_bt.setOnClickListener({
            playOrStopMusic()
        })

        music_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (hasMusic&&fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    private fun playOrStopMusic(){
        if (hasMusic) {
            if (mediaPlayer.isPlaying) {
                play_bt.setBackgroundResource(R.drawable.stop)
                mediaPlayer.pause()
                if( animator!!.isRunning){
                    animator?.cancel()
                    closeAxleAnim()
                }
            } else {
                mediaPlayer.start()
                animator?.start()
                startAxleAnim()
                play_bt.setBackgroundResource(R.drawable.play)
            }
        } else {
            initMedia(0)
        }
    }




    private fun initDialog() {
        dialog = Dialog(this)
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_misic_list, null)
        dialog!!.setContentView(view)
        view.recycle_list.layoutManager = LinearLayoutManager(this)
        val adapter = MusicAdapter(this, musicNameList);
        view.recycle_list.adapter = adapter
        val window = dialog!!.window
        val layoutParams: WindowManager.LayoutParams = window.attributes
        layoutParams.gravity = Gravity.BOTTOM
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = layoutParams

        adapter.setItemClickListener { i ->
            Toast.makeText(this, "" + musicNameList[i], Toast.LENGTH_SHORT).show()
            localPosition = i
            initMedia(i)
            dialog?.dismiss()
        }
    }

    private fun initMedia(position: Int) {
        startAxleAnim()
        play_bt.setBackgroundResource(R.drawable.play)
        hasMusic = true
        localPosition = position
        if (position < 0) {
            localPosition = 0
        }
        if (position >= musicList.size) {
            localPosition = (musicList.size - 1)
        }
        //初始化音乐部分
        try {
            mediaPlayer.reset()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer = MediaPlayer.create(this, musicList[localPosition])
            mediaPlayer.start()
            show_music_name.text = musicNameList[localPosition]
            music_time.text = formatTime(mediaPlayer.duration)
            //唱片旋转
            setMusicAnim()
            //设置进度条
            setBar()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun setMusicAnim() {
        animator?.cancel()
        music_seekbar.progress = 0
        animator = ObjectAnimator.ofFloat(rotate_bg_iv, "rotation", 0f, 360.0f)
        animator!!.let {
            it.duration = 10000
            it.interpolator = LinearInterpolator()
            it.repeatCount = -1 //动画重复
            it.repeatMode = ValueAnimator.RESTART
            it.start()
        }

    }


    private fun startAxleAnim(){
        animatorAxle = RotateAnimation(0f,45f,Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0f)
        animatorAxle!!.let {
            it.fillAfter = true
            it.duration = 1500
        }
        axle_iv.startAnimation(animatorAxle)
    }

    private fun closeAxleAnim(){
        animatorAxle2 = RotateAnimation(45f,0f,Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0f)
        animatorAxle2!!.let {
            it.fillAfter = true
            it.duration = 1500
        }
        axle_iv.startAnimation(animatorAxle2)
    }

    private fun setBar() {
        var mDisposable:Disposable? = null
        music_seekbar.max = mediaPlayer.duration
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take((mediaPlayer.duration+1).toLong())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Long>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        mDisposable = d
                    }

                    override fun onNext(t: Long) {
                        music_seekbar.progress = mediaPlayer.currentPosition
                    }

                    override fun onError(e: Throwable) {
                        mDisposable?.dispose()
                    }
                })
    }


    @CheckResult
    private fun formatTime(length: Int): String {
        val date = Date(length.toLong())
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        return simpleDateFormat.format(date)
    }
}


