package com.example.property.service.impl;

import com.example.property.entity.property.*;
import com.example.property.entity.user.User;
import com.example.property.enumuration.PropertyType;
import com.example.property.enumuration.UnitType;
import com.example.property.repository.property.*;
import com.example.property.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;


@Service
@Slf4j
public class TenantBulkService {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final PropertyRepository propertyRepository;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;
    private final CountryRepository countryRepository;
    private final DistrictRepository districtRepository;
    private final VillageRepository villageRepository;
    private final StreetRepository streetRepository;
    private final CityRepository cityRepository;
    private final PropResPersonRepository propertyResponsePersonRepository;
    private final BuildingResPersonRepository buildingResPersonRepository;


    public TenantBulkService(TenantRepository tenantRepository, PropertyRepository propertyRepository, BuildingRepository buildingRepository,
                             UnitRepository unitRepository, CountryRepository countryRepository, DistrictRepository districtRepository, VillageRepository villageRepository, StreetRepository streetRepository,
                             CityRepository cityRepository, PropResPersonRepository propertyResponsePersonRepository,
                             UserRepository userRepository, BuildingResPersonRepository buildingResPersonRepository) {
        this.tenantRepository = tenantRepository;
        this.propertyRepository = propertyRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
        this.countryRepository = countryRepository;
        this.districtRepository = districtRepository;
        this.villageRepository = villageRepository;
        this.streetRepository = streetRepository;
        this.cityRepository = cityRepository;

        this.propertyResponsePersonRepository = propertyResponsePersonRepository;
        this.userRepository = userRepository;
        this.buildingResPersonRepository = buildingResPersonRepository;
    }

    public String readExcelAndWriteToDatabase(MultipartFile multipartFile) {

        try {
          //  FileInputStream excelFile = new FileInputStream(filePath);

            InputStream inputStream = multipartFile.getInputStream();
            Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            CountryEntity countryEntity = null;
            CityEntity cityEntity = null;
            DistrictEntity districtEntity = null;
            StreetEntity streetEntity = null;
            VillageEntity villageEntity = null;
            PropertyEntity propertyEntity = null;
            BuildingEntity buildingEntity = null;
            UnitEntity unitEntity = null;
            TenantEntity tenantEntity = null;
            PropertyResponsePerson propertyResponsePersonEntity = null;
            BuildingResponsePerson buildingResponsePersonEntity = null;

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            int currentRow = 0;

            while (rowIterator.hasNext()) {


                Row row = rowIterator.next();

                currentRow++;

                if (currentRow <= 0) {
                    continue;
                }


                String countryName = row.getCell(0).getStringCellValue();
                String cityName = row.getCell(1).getStringCellValue();
                String districtName = row.getCell(2).getStringCellValue();
                String streetName = row.getCell(3).getStringCellValue();
                String adressNote = row.getCell(4).getStringCellValue();
                String villageName = row.getCell(5).getStringCellValue();
                String propertyName = row.getCell(6).getStringCellValue();
                PropertyType propertyType = PropertyType.fromDisplayName(row.getCell(7).getStringCellValue());
                int countOfBuildings = (int) (row.getCell(8).getNumericCellValue());
                int countOfUnits = (int) row.getCell(9).getNumericCellValue();
                int bankAccount = (int) row.getCell(10).getNumericCellValue();
                String propertyResponsiblePerson = row.getCell(11).getStringCellValue();
                String responsiblePersonEmail = row.getCell(12).getStringCellValue();
                int propertyArea = (int) row.getCell(13).getNumericCellValue();

                Cell buildingNameCell = row.getCell(14);
                String buildingName;

                if (buildingNameCell.getCellType() == CellType.STRING) {
                    buildingName = buildingNameCell.getStringCellValue();
                } else if (buildingNameCell.getCellType() == CellType.NUMERIC) {
                    buildingName = String.valueOf((int) buildingNameCell.getNumericCellValue());
                } else {
                    buildingName = "";
                }

                int countOfEntrance = (int) row.getCell(15).getNumericCellValue();
                int countOfFloors = (int) row.getCell(16).getNumericCellValue();
                int numberOfUnits = (int) row.getCell(17).getNumericCellValue();
                String description = row.getCell(18).getStringCellValue();
                String buildingResponsiblePerson = row.getCell(19).getStringCellValue();
                int buildingArea = (int) row.getCell(20).getNumericCellValue();
                String noteForBuilding = row.getCell(21).getStringCellValue();
                UnitType unitType = UnitType.fromDisplayName(row.getCell(22).getStringCellValue());

                Cell unitNumberCell = row.getCell(23);
                String unitNumber;

                if (unitNumberCell == null) {
                    unitNumber = " ";
                } else if (unitNumberCell.getCellType() == CellType.NUMERIC) {
                    unitNumber = String.valueOf((int) unitNumberCell.getNumericCellValue());
                } else if (unitNumberCell.getCellType() == CellType.STRING) {
                    unitNumber = (unitNumberCell.getStringCellValue());
                } else {
                    unitNumber = (" ");
                }

                int unitFloor = (int) row.getCell(24).getNumericCellValue();
                int unitRooms = (int) row.getCell(25).getNumericCellValue();
                int unitArea = (int) row.getCell(26).getNumericCellValue();
                String unitNotes = row.getCell(27).getStringCellValue();
                String tenantName = row.getCell(28).getStringCellValue();
                String tenantSurname = row.getCell(29).getStringCellValue();

                Cell tenantFatherNameCell = row.getCell(30);
                String tenantFatherName;
                if (tenantFatherNameCell == null) {
                    tenantFatherName = null;
                } else {
                    tenantFatherName = row.getCell(30).getStringCellValue();
                }

                String tenantPin = row.getCell(31).getStringCellValue();
                String tenantHomeNumber = row.getCell(32).getStringCellValue();

                String tenantPhoneNumber1 = row.getCell(33).getStringCellValue();

                Cell tenantCallNumber1Cell = row.getCell(34);
                Boolean tenantCallNumber1;
                if (tenantCallNumber1Cell == null) {
                    tenantCallNumber1 = null;
                } else {
                    tenantCallNumber1 = Boolean.valueOf(row.getCell(34).getStringCellValue());
                }

                Cell tenantWhatsappNumber1Cell = row.getCell(35);
                Boolean tenantWhatsappNumber1;
                if (tenantWhatsappNumber1Cell == null) {
                    tenantWhatsappNumber1 = null;
                } else {
                    tenantWhatsappNumber1 = Boolean.valueOf(row.getCell(35).getStringCellValue());
                }

                Cell tenantSmsNumber1Cell = row.getCell(36);
                Boolean tenantSmsNumber1;
                if (tenantSmsNumber1Cell == null) {
                    tenantSmsNumber1 = null;
                } else {
                    tenantSmsNumber1 = Boolean.valueOf(row.getCell(36).getStringCellValue());
                }


                Cell tenantPhoneNumber2Cell = row.getCell(37);
                String tenantPhoneNumber2;
                if (tenantPhoneNumber2Cell == null) {
                    tenantPhoneNumber2 = null;
                } else {
                    tenantPhoneNumber2 = row.getCell(37).getStringCellValue();
                }

                Cell tenantCallNumber2Cell = row.getCell(38);
                Boolean tenantCallNumber2;
                if (tenantCallNumber2Cell == null) {
                    tenantCallNumber2 = null;
                } else {
                    tenantCallNumber2 = Boolean.valueOf(row.getCell(38).getStringCellValue());
                }

                Cell tenantWhatsappNumber2Cell = row.getCell(39);
                Boolean tenantWhatsappNumber2;
                if (tenantWhatsappNumber2Cell == null) {
                    tenantWhatsappNumber2 = null;
                } else {
                    tenantWhatsappNumber2 = Boolean.valueOf(row.getCell(39).getStringCellValue());
                }

                Cell tenantSmsNumber2Cell = row.getCell(40);
                Boolean tenantSmsNumber2;
                if (tenantSmsNumber2Cell == null) {
                    tenantSmsNumber2 = null;
                } else {
                    tenantSmsNumber2 = Boolean.valueOf(row.getCell(40).getStringCellValue());
                }


                String tenantEmail = row.getCell(41).getStringCellValue();


                if (!Objects.isNull(countryRepository.getCountryEntityByCountryName(countryName))) {
                    countryEntity = countryRepository.getCountryEntityByCountryName(countryName);
                } else {
                    countryEntity = CountryEntity.builder().countryName(countryName).build();
                    countryEntity = countryRepository.save(countryEntity);
                }

                if (!Objects.isNull(cityRepository.getCityEntityByCityName(cityName))) {
                    cityEntity = cityRepository.getCityEntityByCityName(cityName);
                } else {
                    cityEntity = CityEntity.builder().cityName(cityName).countryEntity(countryEntity).build();
                    cityRepository.save(cityEntity);
                }

                if (!Objects.isNull(districtRepository.getDistrictEntityByDistrictName(districtName))) {
                    districtEntity = districtRepository.getDistrictEntityByDistrictName(districtName);
                } else {
                    districtEntity = DistrictEntity.builder().districtName(districtName).cityEntity(cityEntity).build();
                    districtRepository.save(districtEntity);
                }

                if (!Objects.isNull(streetRepository.getStreetEntityByStreetName(streetName))) {
                    streetEntity = streetRepository.getStreetEntityByStreetName(streetName);
                } else {
                    streetEntity = StreetEntity.builder().streetName(streetName).districtEntity(districtEntity).build();
                    streetRepository.save(streetEntity);
                }

                if (!Objects.isNull(villageRepository.getVillageEntityByVillageName(villageName))) {
                    villageEntity = villageRepository.getVillageEntityByVillageName(villageName);
                } else {
                    villageEntity = VillageEntity.builder().villageName(villageName).districtEntity(districtEntity).build();
                    villageRepository.save(villageEntity);
                }

                if (!Objects.isNull(propertyRepository.getPropertyEntityByName(propertyName))) {
                    propertyEntity = propertyRepository.getPropertyEntityByName(propertyName);
                } else {
                    propertyEntity = PropertyEntity.builder()
                            .name(propertyName)
                            .addressNote(adressNote)
                            .propertyType((propertyType))
                            .countBuilding((countOfBuildings))
                            .countUnit((countOfUnits))
                            .bankAccount(String.valueOf(bankAccount))
                            .countryEntity(countryEntity)
                            .area((propertyArea))
                            .email(responsiblePersonEmail)
                            .cityEntity(cityEntity)
                            .districtEntity(districtEntity)
                            .streetEntity(streetEntity)
                            .villageEntity(villageEntity)
                            .build();

                    propertyRepository.save(propertyEntity);
                }

                //propertyResponsiblePerson
//
                if (!Objects.isNull(propertyResponsePersonRepository.getPropertyResponsePersonByPropertyEntity(propertyEntity))) {
                    propertyResponsePersonRepository.getPropertyResponsePersonByPropertyEntity(propertyEntity);
                } else {
                    User byFullName = userRepository.findByFullName(propertyResponsiblePerson);
                    propertyResponsePersonEntity = PropertyResponsePerson.builder()
                            .propertyEntity(propertyEntity)
                            .user(byFullName)
                            .build();

                    propertyResponsePersonRepository.save(propertyResponsePersonEntity);
                }


                if (!Objects.isNull(buildingRepository.getBuildingEntityByName(buildingName))) {
                    buildingEntity = buildingRepository.getBuildingEntityByName(buildingName);
                } else {
                    buildingEntity = BuildingEntity.builder()
                            .name(buildingName)
                            .countEntrance((countOfEntrance))
                            .countFloor((countOfFloors))
                            .countUnit((numberOfUnits))
                            .description(description)
                            .area((buildingArea))
                            .note(noteForBuilding)
                            .propertyEntity(propertyEntity)
                            .build();
                    buildingRepository.save(buildingEntity);
                }


                if (!Objects.isNull((buildingResPersonRepository.getPropertyResponsePersonByBuildingEntity(buildingEntity)))) {
                    buildingResPersonRepository.getPropertyResponsePersonByBuildingEntity(buildingEntity);
                } else {
                    User byFullName = userRepository.findByFullName(buildingResponsiblePerson);
                    buildingResponsePersonEntity = BuildingResponsePerson.builder()
                            .buildingEntity(buildingEntity)
                            .user(byFullName)
                            .build();

                    buildingResPersonRepository.save(buildingResponsePersonEntity);
                }


                if (!Objects.isNull(unitRepository.getUnitEntityByUnitNumber(String.valueOf(unitNumber)))) {
                    unitEntity = unitRepository.getUnitEntityByUnitNumber(String.valueOf(unitNumber));
                } else {
                    unitEntity = UnitEntity.builder()
                            .unitNumber(String.valueOf(unitNumber))
                            .unitType((unitType))
                            .floor((unitFloor))
                            .countRoom((unitRooms))
                            .area((unitArea))
                            .note(unitNotes)
                            .buildingEntity(buildingEntity)
                            .build();
                    unitRepository.save(unitEntity);
                }

                if (!Objects.isNull(tenantRepository.getTenantEntityByName(tenantName))) {
                    tenantEntity = tenantRepository.getTenantEntityByName(tenantName);

                } else {
                    tenantEntity = TenantEntity.builder()
                            .name(tenantName)
                            .surname(tenantSurname)
                            .fatherName(tenantFatherName)
                            .pin(tenantPin)
                            .phoneNumber1(tenantPhoneNumber1)
                            .phoneNumber2(tenantPhoneNumber2)
                            .homeNumber(tenantHomeNumber)
                            .callPhoneNumber1(tenantCallNumber1)
                            .wpPhoneNumber1(tenantWhatsappNumber1)
                            .smsPhoneNumber1(tenantSmsNumber1)
                            .callPhoneNumber2(tenantCallNumber2)
                            .wpPhoneNumber2(tenantWhatsappNumber2)
                            .smsPhoneNumber2(tenantSmsNumber2)
                            .email(tenantEmail)
                            .unitEntity(unitEntity)
                            .build();

                    tenantRepository.save(tenantEntity);
                }

                log.info(String.valueOf(row.getRowNum()));

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return "Succes";
    }
}