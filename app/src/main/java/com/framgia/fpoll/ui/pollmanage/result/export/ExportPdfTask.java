package com.framgia.fpoll.ui.pollmanage.result.export;

import android.os.AsyncTask;
import android.os.Environment;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import static com.framgia.fpoll.util.Constant.Export.FILE_NAME_SAVED_PDF;
import static com.framgia.fpoll.util.Constant.Export.FPOLL_FOLDER_NAME;
import static com.framgia.fpoll.util.Constant.Export.TITLE_NAME;
import static com.framgia.fpoll.util.Constant.Export.TITLE_VOTE;
import static com.framgia.fpoll.util.TimeUtil.getCurentTime;

/**
 * Created by tuanbg on 3/15/17.
 */
public class ExportPdfTask extends AsyncTask<Void, String, String> {
    private static final int NUMBER_COLUMN_TABLE = 2;
    private List<ResultVoteItem.Result> mList;
    private ExportExcelTask.CallBackExport mCallBackExport;

    public ExportPdfTask(List<ResultVoteItem.Result> list,
                         ExportExcelTask.CallBackExport callBackExport) {
        mList = list;
        mCallBackExport = callBackExport;
    }

    @Override
    protected String doInBackground(Void... params) {
        return createPdf();
    }

    private String createPdf() {
        File exportDir =
            new File(Environment.getExternalStorageDirectory(), FPOLL_FOLDER_NAME);
        if (!exportDir.exists()) exportDir.mkdirs();
        File file = new File(exportDir, getCurentTime() + FILE_NAME_SAVED_PDF);
        OutputStream output;
        try {
            output = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, output);
            document.open();
            document.add(generatePDFTable());
            document.close();
            return file.getPath();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return "";
        }
    }

    private PdfPTable generatePDFTable() {
        // 2 columns table
        PdfPTable table = new PdfPTable(NUMBER_COLUMN_TABLE);
        table.addCell(TITLE_NAME);
        table.addCell(TITLE_VOTE);
        for (int i = 0; i < mList.size(); i++) {
            table.addCell(mList.get(i).getName());
            table.addCell(String.valueOf(mList.get(i).getVoters()));
        }
        return table;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.isEmpty()) mCallBackExport.onExportSuccess(s);
        else mCallBackExport.onExportError();
    }
}
