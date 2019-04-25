package com.dnamedical.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.dnamedical.Adapters.ResultAdapter;
import com.dnamedical.Adapters.result;
import com.dnamedical.Models.ResultData.AllReult;
import com.dnamedical.Models.ResultData.ResultList;
import com.dnamedical.Models.ResultData.UserResult;
import com.dnamedical.R;
import com.dnamedical.Retrofit.RestClient;
import com.dnamedical.utils.DnaPrefs;
import com.dnamedical.utils.Utils;

import hiennguyen.me.circleseekbar.CircleSeekBar;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {


    TextView dateTv, percentValue, testNameTv, total, skipped, wrong, correct;

    private List<UserResult> userResults;
    private List<ResultList> resultLists;
    private List<AllReult> allReults;
    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;
    private Button reviewButton, shareButton;
    private CircleSeekBar circleSeekBar;
    String user_id;
    String tquestion, average, canswer, wanswer, sanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // dateTv = findViewById(R.id.date);
        percentValue = findViewById(R.id.percentageValue);
        //  testNameTv = findViewById(R.id.testName);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        reviewButton = findViewById(R.id.review);
        circleSeekBar = findViewById(R.id.circular);
        shareButton = findViewById(R.id.btn_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "DNA");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.dnamedical");
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });

        total = findViewById(R.id.total_question);
        skipped = findViewById(R.id.skipped);
        wrong = findViewById(R.id.wrong);
        correct = findViewById(R.id.correct);

        showRankResult();


        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewSheet();
            }
        });

/*

        if (getIntent().hasExtra("tquestion")) {
            Intent intent = getIntent();
            average = intent.getStringExtra("average");
            String userid = intent.getStringExtra("user_Id");
            tquestion = intent.getStringExtra("tquestion");
            canswer = intent.getStringExtra("canswer");
            wanswer = intent.getStringExtra("wanswer");
            sanswer = intent.getStringExtra("sanswer");
            String testName = intent.getStringExtra("testName");
            percentValue.setText("  " + average);
            circleSeekBar.setProgressDisplay(Integer.parseInt(canswer));
            total.setText(tquestion);
            correct.setText(canswer);
            wrong.setText(wanswer);
            skipped.setText(sanswer);

        }

*/

        //dateTv.setText(Utils.tripDateFormat(System.currentTimeMillis()));

        //testNameTv.setText("" + testName);

    }

    private void ReviewSheet() {
        String test_id = getIntent().getStringExtra("Test_Id");
        Intent intent = new Intent(ResultActivity.this, ReviewQuestionList.class);
        intent.putExtra("id", test_id);
        startActivity(intent);
    }

    private void showRankResult() {
        String user_id;
        if (DnaPrefs.getBoolean(getApplicationContext(), "isFacebook")) {
            user_id = String.valueOf(DnaPrefs.getInt(getApplicationContext(), "fB_ID", 0));
        } else {
            user_id = DnaPrefs.getString(getApplicationContext(), "Login_Id");
        }

        String testid = getIntent().getStringExtra("Test_Id");


        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody testId = RequestBody.create(MediaType.parse("text/plain"), testid);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.resultList(userId, testId, new Callback<ResultList>() {
                @Override
                public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                    Utils.dismissProgressDialog();

                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("1")) {
                            userResults = response.body().getUserResult();
                            total.setText(userResults.get(0).getTotalQuestion());
                            skipped.setText(userResults.get(0).getSkipQuestion());

                            if (!(userResults.get(0).getCurrectQuestion() != null)
                                    && TextUtils.isEmpty(userResults.get(0).getCurrectQuestion())) {
                                    correct.setText("0");
                            } else {
                                correct.setText(userResults.get(0).getCurrectQuestion());
                            }

                            wrong.setText(userResults.get(0).getWrongQuestion());
                            percentValue.setText(userResults.get(0).getAverage());
                            allReults = response.body().getAllReult();
                            resultAdapter = new ResultAdapter(allReults);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(resultAdapter);

                        } else {
                            Toast.makeText(ResultActivity.this, "Invalid Status", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ResultActivity.this, "Response is Invalid", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResultList> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(ResultActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }


}
