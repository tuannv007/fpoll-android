package com.framgia.fpoll.ui.pollmanage.result.export;

import android.os.AsyncTask;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import static com.framgia.fpoll.util.Constant.Export.TITLE_NAME;
import static com.framgia.fpoll.util.Constant.Export.TITLE_VOTE;

public class ExportExcelTask extends AsyncTask<Void, String, String> {
    private File mFile;
    private List<ResultVoteItem.Result> mList;
    private CallBackExport mCallBackExport;

    public ExportExcelTask(File file, List<ResultVoteItem.Result> list,
                           CallBackExport callBackExport) {
        mFile = file;
        mList = list;
        mCallBackExport = callBackExport;
    }

    private String export() {
        try {
            mFile.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(mFile));
            //Headers
            String title[] = {TITLE_NAME, TITLE_VOTE};
            csvWrite.writeNext(title);
            for (int i = 0; i < mList.size(); i++) {
                String arrStr[] = {
                    mList.get(i).getName(),
                    String.valueOf(mList.get(i).getVoters()),
                };
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            return mFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        return export();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.isEmpty()) mCallBackExport.onExportSuccess(s);
        else mCallBackExport.onExportError();
    }

    public interface CallBackExport {
        void onExportError();
        void onExportSuccess(String path);
    }
}
