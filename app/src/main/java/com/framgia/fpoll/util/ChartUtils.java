package com.framgia.fpoll.util;

import android.content.Context;

import com.framgia.fpoll.R;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhtv on 15/03/2017.
 */
public class ChartUtils {
    public static List<String> createLabels(VoteInfoModel voteInfoModel) {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < voteInfoModel.getOptionModels().size(); i++) {
            labels.add(voteInfoModel.getOptionModels().get(i).getName());
        }
        return labels;
    }

    public static PieDataSet createPieData(Context context, VoteInfoModel voteInfoModel) {
        List<Entry> pieEntries = new ArrayList<>();
        for (int i = 0; i < voteInfoModel.getOptionModels().size(); i++) {
            int totalVote =
                voteInfoModel.getOptionModels().get(i).getParticipantVotes().size()
                    + voteInfoModel.getOptionModels().get(i).getVotes().size();
            pieEntries.add(new Entry(totalVote, i));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setValueTextSize(context.getResources().getDimension(R.dimen.sp_16));
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieDataSet.setValueFormatter(new RoundFormatter());
        return pieDataSet;
    }

    public static BarDataSet createBarData(Context context, VoteInfoModel voteInfoModel) {
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < voteInfoModel.getOptionModels().size(); i++) {
            int totalVote =
                voteInfoModel.getOptionModels().get(i).getParticipantVotes().size()
                    + voteInfoModel.getOptionModels().get(i).getVotes().size();
            barEntries.add(new BarEntry(totalVote, i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setValueTextSize(context.getResources().getDimension(R.dimen.sp_16));
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueFormatter(new RoundFormatter());
        barDataSet.setBarSpacePercent(50f);
        return barDataSet;
    }

    private static class RoundFormatter implements ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return Math.round(value) + "";
        }
    }
}
