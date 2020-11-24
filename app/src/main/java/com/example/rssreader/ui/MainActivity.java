package com.example.rssreader.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssreader.databinding.ActivityMainBinding;
import com.example.rssreader.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initRecyclerView();

        List<News> news = new ArrayList<News>();
        news.add(new News(1L, "", "https://img.tyt.by/thumbnails/n/obshchestvo/09/2/artem_sorokin_.jpg", "«Весь коллектив БСМП знает, что этанол был по нулям». Медики - о враче, который находится в СИЗО", "24 Nov 2020 16:33:00"));
        news.add(new News(2L, "", "https://img.tyt.by/thumbnails/n/shukaylo/0d/c/viktor_babariko_20200611_shuk_tutby_phsl_8635.jpg", "Виктору Бабарико и топ-менеджерам по «делу Белгазпромбанка» предъявили обвинение", "24 Nov 2020 15:16:00"));
        news.add(new News(3L, "", "https://img.tyt.by/thumbnails/n/politika/03/b/mirotvorcy_foto_arhano.ru.jpg", "Беларусь планирует увеличить число миротворцев за рубежом. Разобрались, куда они могут поехать", "24 Nov 2020 15:11:00"));
        news.add(new News(4L, "", "https://img.tyt.by/thumbnails/n/buryakina/03/4/sud_ubiystvo_mogilev_20191117_bur_tutby_phsl-6030.jpg", "«Я виноват? Тогда смертный приговор». Обвиняемые в убийстве семьи под Белыничами выступают с последним словом", "24 Nov 2020 14:57:00"));
        news.add(new News(5L, "", "https://img.tyt.by/thumbnails/n/shukaylo/02/c/skoraya_pomoshch_koronavirus_2020424_shuk_tutby_phsl_0640.jpg", "COVID-19 в Беларуси за сутки: тестов сделали меньше, новых случаев - 1471, смертей - 8", "24 Nov 2020 14:29:00"));
        news.add(new News(6L, "", "https://img.tyt.by/thumbnails/n/07/2/evroparlament-kken993491371.jpg", "Депутаты Европарламента на дебатах обсудят смерть Романа Бондаренко", "24 Nov 2020 13:05:00"));
        news.add(new News(7L, "", "https://img.tyt.by/thumbnails/n/zamirovskiy/00/2/05_sud_tutbay_20201124_zam_tutby_phsl.jpg", "Иск Мининформа против TUT.BY: в суде говорят, СМИ нарушило право читателей на достоверную информацию о госорганах", "24 Nov 2020 12:26:00"));

        newsAdapter.submitList(news);
    }

    private void initRecyclerView() {
        newsAdapter = new NewsAdapter();
        RecyclerView recyclerView = binding.mainRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }
}