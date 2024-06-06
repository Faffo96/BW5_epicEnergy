package it.epicode.BW2_EpicEnergyServices.Service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import it.epicode.BW2_EpicEnergyServices.Dto.ProvinceDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Province;
import it.epicode.BW2_EpicEnergyServices.Exceptions.ProvinceNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public String saveProvince(ProvinceDto provinceDto) {
        Province province = new Province();
        province.setAcronym(provinceDto.getAcronym());
        province.setName(provinceDto.getName());

        provinceRepository.save(province);

        return "Province with id " + province.getProvinceId() + " correctly saved!";
    }

    public Page<Province> getAllProvince(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return provinceRepository.findAll(pageable);
    }

    public Province getProvinceById(int id) {
        return provinceRepository.findById(id)
                .orElseThrow(() -> new ProvinceNotFoundException("Province not found with id " + id));
    }


    public String updateProvince(int id, ProvinceDto provinceDto) {
        Province province = getProvinceById(id); // Usa il metodo che non restituisce Optional
        province.setAcronym(provinceDto.getAcronym());
        province.setName(provinceDto.getName());

        provinceRepository.save(province);

        return "Province with id " + province.getProvinceId() + " correctly saved!";
    }

    public String deleteProvince(int id) {
        Province province = getProvinceById(id); // Usa il metodo che non restituisce Optional
        provinceRepository.deleteById(id);
        return "Province with id=" + id + " correctly deleted!";
    }


    @Transactional
    public void importProvincesFromCSV(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            csvReader.skip(1);

            List<String[]> rows = csvReader.readAll();

            for (String[] row : rows) {
                System.out.println("Raw row length: " + row.length); // Stampa la lunghezza della riga
                System.out.println("Raw row: " + Arrays.toString(row)); // Stampa l'array come stringa leggibile

                // Rimuovi gli spazi bianchi da ciascun elemento dell'array
                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i].trim();
                }

                System.out.println("Trimmed row length: " + row.length); // Stampa la lunghezza della riga dopo il trim
                System.out.println("Trimmed row: " + Arrays.toString(row)); // Stampa l'array modificato

                if (row.length >= 3) { // Assicurati che ci siano abbastanza colonne
                    String acronym = row[0];
                    String provinceName = row[1];

                    System.out.println("Acronym: " + acronym);
                    System.out.println("ProvinceName: " + provinceName);

                    Province province = new Province();
                    province.setAcronym(acronym);
                    province.setName(provinceName);

                    // Salva la provincia nel repository
                    provinceRepository.save(province);
                } else {
                    System.out.println("Skipped row due to insufficient length: " + Arrays.toString(row));
                }
            }
        }
    }


    public Province getProvinceByName(String name) {
        Optional<Province> optionalProvince = provinceRepository.findByName(name);
        return optionalProvince.orElseThrow(() -> new RuntimeException("Province not found"));
    }
}
