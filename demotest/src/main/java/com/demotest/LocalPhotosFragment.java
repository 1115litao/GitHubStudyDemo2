package com.demotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.demotest.adapter.PhotoAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LocalPhotosFragment extends Fragment {

    private final static String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID};
    private GridView gv_fragment_photos;
    private Button bt_ok;
    private CursorLoader cursorLoader;
    private ArrayList<String> allList;
    private ArrayList<String> photoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_photos, container, false);
        initView(view);
        initPermissions();
        return view;
    }

    private void initPermissions() {

        ArrayList<String> permissList = new ArrayList();
        permissList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ArrayList<String> permissList2 = new ArrayList();
        permissList2.clear();
        for ( int i=0;i<permissList.size();i++){
            if(ContextCompat.checkSelfPermission(getActivity(),permissList.get(i) )!= PackageManager.PERMISSION_GRANTED){
                permissList2.add(permissList.get(i));
            }
        }
        if(!permissList2.isEmpty()){
            String[]  permiss = permissList2.toArray(new String[permissList2.size()]);
            LocalPhotosFragment.this.requestPermissions(permiss,2);
        }else{
            showImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 2){
            for (int i=0;i<grantResults.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    showImage();
                }else{

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView(View view) {
        gv_fragment_photos = view.findViewById(R.id.gv_fragment_photos);
        bt_ok = view.findViewById(R.id.bt_ok);
    }

    public void showImage(){
        loadAllImage(new photoInterface() {
            @Override
            public void getPythoto(ArrayList<String> list) {
                Log.i("aaa",""+list.size());
                gv_fragment_photos.setAdapter(new PhotoAdapter(getActivity(),list));
            }
        });
    }



    /**
     * 加载手机所有图片
     */
    public   void  loadAllImage(final photoInterface  photoInterface) {
        getActivity().getSupportLoaderManager().initLoader(1, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader cursorLoader = null;
                if (id == 1) {
                    cursorLoader = new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_PROJECTION,
                            MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                            new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_ADDED + " DESC");
                }
                return cursorLoader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                allList = new ArrayList<>();
                while (data!=null && data.moveToNext()){
                    String path = data.getString(data.getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.i("aaa",""+path);
                    allList.add(path);
                }
                photoInterface.getPythoto(allList);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
            }
        });


//        getActivity().getSupportLoaderManager().initLoader()
    }

    interface  photoInterface{
        void getPythoto(ArrayList<String> list);
    }

}
