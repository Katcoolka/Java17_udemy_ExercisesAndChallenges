package dev.lpa;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        //!!!remove comment in the row below for correct outcomes!!!

        //System.setProperty("user.timezone", "America/Los_Angeles"); //setting system clocks, before any other code
        //America/Los_Angeles
        //System.setProperty("user.timezone", "UTC");
        System.out.println("<<<----------- Time Zones --------------->>>\n");
        //ZoneId is backward compatible
        System.out.println(ZoneId.systemDefault()); //Europe/Bratislava
        System.out.println("Number of TZs = " + ZoneId.getAvailableZoneIds().size()); //Number of TZs = 603
        System.out.println("*".repeat(30));

        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.startsWith("US"))
                .sorted()
                .map(ZoneId::of)
                .forEach(z -> System.out.println(z.getId() + ": " + z.getRules()));

        System.out.println("*".repeat(30));

        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
        Set<String> oldway = new HashSet<>(Set.of(alternate));

        jdk8Zones.removeAll(oldway);
        System.out.println(jdk8Zones); //output: []

        oldway.removeAll(jdk8Zones);
        System.out.println(oldway);//[Asia/Aden, America/Cuiaba, Etc/GMT+9, Etc/GMT+8, Africa/Nairobi, America/Marigot, Asia/Aqtau, Pacific/Kwajalein, America/El_Salvador, Asia/Pontianak, Africa/Cairo, Pacific/Pago_Pago, Pacific/Rarotonga, Pacific/Honolulu, Asia/Kuching, Africa/Mbabane, America/Guatemala, Australia/Hobart, Europe/London, America/Panama, America/Belize, Asia/Chungking, America/Managua, Asia/Yerevan, Europe/Brussels, America/Indiana/Petersburg, JST, GMT, Europe/Warsaw, America/Chicago, Asia/Kashgar, Chile/Continental, Pacific/Yap, CET, Etc/GMT-1, Europe/Jersey, Etc/GMT-0, Europe/Istanbul, Etc/GMT-5, America/Tegucigalpa, America/Eirunepe, Etc/GMT-4, America/Miquelon, Europe/Luxembourg, Etc/GMT-3, Etc/GMT-2, Etc/GMT-9, America/Argentina/Catamarca, Etc/GMT-8, Etc/GMT-7, Etc/GMT-6, Europe/Zaporozhye, Canada/Yukon, Canada/Atlantic, Atlantic/St_Helena, Libya, Australia/Tasmania, Europe/Guernsey, America/Grand_Turk, Asia/Samarkand, America/Argentina/Cordoba, Asia/Phnom_Penh, IST, Africa/Kigali, Asia/Almaty, US/Alaska, Europe/Isle_of_Man, Asia/Dubai, BET, America/Araguaina, Cuba, ACT, Asia/Novosibirsk, America/Argentina/Salta, Etc/GMT+3, Africa/Tunis, Etc/GMT+2, Pacific/Fakaofo, Etc/GMT+1, Israel, Africa/Tripoli, Etc/GMT+0, Africa/Banjul, Indian/Comoro, Etc/GMT+7, Etc/GMT+6, Etc/GMT+5, Etc/GMT+4, Pacific/Port_Moresby, US/Arizona, Antarctica/Syowa, Indian/Reunion, Pacific/Palau, Europe/Kaliningrad, America/Montevideo, Africa/Windhoek, Asia/Karachi, Africa/Mogadishu, Brazil/East, Australia/Perth, Etc/GMT, Asia/Chita, Pacific/Easter, Antarctica/Davis, Antarctica/McMurdo, Asia/Macao, America/Manaus, Africa/Freetown, Europe/Bucharest, Asia/Tomsk, Asia/Macau, America/Argentina/Mendoza, Europe/Malta, HST, Pacific/Tahiti, Mexico/BajaSur, Africa/Asmera, Europe/Busingen, AET, America/Argentina/Rio_Gallegos, Africa/Malabo, Europe/Skopje, America/Catamarca, America/Godthab, Europe/Sarajevo, Australia/ACT, GB-Eire, Africa/Lagos, Europe/Rome, America/Cordoba, Asia/Dacca, Indian/Mauritius, Pacific/Samoa, America/Regina, America/Fort_Wayne, America/Dawson_Creek, Africa/Algiers, Europe/Mariehamn, America/St_Johns, Europe/Zurich, America/St_Thomas, America/Anguilla, Asia/Dili, America/Denver, Europe/Saratov, Africa/Bamako, GB, Mexico/General, Pacific/Wallis, Europe/Gibraltar, Africa/Conakry, Africa/Lubumbashi, Asia/Istanbul, America/Havana, NZ-CHAT, Asia/Choibalsan, Asia/Omsk, America/Porto_Acre, AGT, Europe/Vaduz, US/Michigan, Asia/Dhaka, America/Barbados, Europe/Tiraspol, Atlantic/Cape_Verde, Asia/Yekaterinburg, America/Louisville, Pacific/Johnston, Pacific/Chatham, Europe/Ljubljana, Asia/Jayapura, America/Sao_Paulo, America/Curacao, Asia/Dushanbe, America/Guyana, Portugal, America/Guayaquil, America/Martinique, Europe/Berlin, Europe/Moscow, Europe/Chisinau, Pacific/Ponape, America/Rankin_Inlet, America/Puerto_Rico, Europe/Stockholm, Europe/Budapest, America/Argentina/Jujuy, Australia/Eucla, Asia/Shanghai, Universal, Europe/Zagreb, America/Port_of_Spain, Europe/Helsinki, Asia/Beirut, Asia/Tel_Aviv, Pacific/Bougainville, US/Central, Africa/Sao_Tome, Indian/Chagos, America/Cayenne, Asia/Yakutsk, Pacific/Galapagos, Australia/North, VST, Europe/Paris, Africa/Ndjamena, Pacific/Fiji, Indian/Maldives, America/Rainy_River, SystemV/AST4, Australia/Yancowinna, Asia/Oral, America/Yellowknife, Pacific/Enderbury, America/Juneau, Australia/Victoria, CNT, America/Indiana/Vevay, Asia/Tashkent, Asia/Jakarta, Asia/Barnaul, Africa/Ceuta, America/Recife, America/Buenos_Aires, America/Noronha, America/Swift_Current, Australia/Adelaide, America/Metlakatla, America/Paramaribo, Africa/Djibouti, EST, Asia/Qostanay, Europe/Simferopol, Europe/Sofia, Africa/Nouakchott, Europe/Prague, America/Indiana/Vincennes, Antarctica/Mawson, America/Kralendijk, Antarctica/Troll, Europe/Samara, Indian/Christmas, America/Antigua, NET, Pacific/Gambier, America/Indianapolis, America/Inuvik, Pacific/Funafuti, America/Iqaluit, UTC, Antarctica/Macquarie, Canada/Pacific, America/Moncton, Africa/Gaborone, Pacific/Chuuk, Asia/Pyongyang, America/St_Vincent, Asia/Gaza, Etc/Universal, PST8PDT, Atlantic/Faeroe, Canada/Newfoundland, Asia/Qyzylorda, America/Yakutat, America/Kentucky/Louisville, Asia/Ho_Chi_Minh, America/Ciudad_Juarez, Antarctica/Casey, Europe/Copenhagen, Atlantic/Azores, Africa/Asmara, Europe/Vienna, ROK, Pacific/Pitcairn, America/Mazatlan, Australia/Queensland, Pacific/Nauru, Europe/Tirane, SystemV/MST7, Asia/Kolkata, Australia/Canberra, MET, Australia/Broken_Hill, Europe/Riga, America/Dominica, Africa/Abidjan, America/Mendoza, America/Santarem, Kwajalein, America/Asuncion, Asia/Ulan_Bator, NZ, America/Boise, Australia/Currie, EST5EDT, Pacific/Guam, Pacific/Wake, Atlantic/Bermuda, America/Costa_Rica, America/Dawson, Eire, Europe/Amsterdam, Asia/Chongqing, America/Indiana/Knox, PLT, America/North_Dakota/Beulah, Africa/Accra, Mexico/BajaNorte, Atlantic/Faroe, Pacific/Apia, Etc/UCT, America/Maceio, GMT0, Pacific/Niue, America/Atka, CST, Australia/Lord_Howe, Europe/Dublin, Pacific/Truk, MST7MDT, America/Monterrey, America/Nassau, SST, America/Jamaica, Asia/Bishkek, America/Atikokan, Atlantic/Stanley, Australia/NSW, US/Hawaii, SystemV/CST6, Indian/Mahe, CTT, Asia/Aqtobe, America/Sitka, Asia/Vladivostok, Africa/Libreville, Africa/Maputo, Zulu, America/Kentucky/Monticello, Africa/El_Aaiun, Africa/Ouagadougou, Pacific/Marquesas, America/Coral_Harbour, Brazil/West, America/Aruba, America/North_Dakota/Center, PNT, America/Cayman, Asia/Ulaanbaatar, Asia/Baghdad, Europe/San_Marino, America/Indiana/Tell_City, BST, America/Tijuana, Pacific/Saipan, SystemV/YST9, Africa/Douala, America/Chihuahua, America/Ojinaga, Asia/Hovd, America/Anchorage, Chile/EasterIsland, America/Halifax, Antarctica/Rothera, US/Mountain, America/Indiana/Indianapolis, Asia/Damascus, MIT, America/Argentina/San_Luis, America/Santiago, Asia/Baku, ART, America/Argentina/Ushuaia, Atlantic/Reykjavik, Africa/Brazzaville, America/La_Paz, Antarctica/DumontDUrville, Africa/Porto-Novo, Asia/Taipei, Asia/Manila, Antarctica/South_Pole, Asia/Bangkok, Africa/Dar_es_Salaam, Poland, Atlantic/Madeira, Antarctica/Palmer, America/Thunder_Bay, Africa/Addis_Ababa, AST, Asia/Yangon, Europe/Uzhgorod, Brazil/DeNoronha, Etc/Zulu, Asia/Ashkhabad, America/Indiana/Marengo, America/Punta_Arenas, America/Creston, America/Mexico_City, Antarctica/Vostok, Asia/Jerusalem, Europe/Andorra, US/Samoa, PRC, Asia/Vientiane, Pacific/Kiritimati, America/Matamoros, America/Blanc-Sablon, Asia/Riyadh, Iceland, Pacific/Pohnpei, Atlantic/South_Georgia, Asia/Ujung_Pandang, Europe/Lisbon, Asia/Harbin, PRT, Europe/Oslo, Asia/Novokuznetsk, CST6CDT, Atlantic/Canary, America/Knox_IN, Asia/Kuwait, SystemV/HST10, Pacific/Efate, Africa/Lome, America/Menominee, America/Bogota, America/Adak, Pacific/Norfolk, Europe/Kirov, America/Resolute, Pacific/Tarawa, Pacific/Kanton, Greenwich, Asia/Krasnoyarsk, Africa/Kampala, PST, SystemV/EST5, Europe/Podgorica, America/Edmonton, Canada/Central, Australia/South, America/Santo_Domingo, IET, Africa/Bujumbura, US/Eastern, Pacific/Auckland, Europe/Minsk, America/Glace_Bay, Africa/Casablanca, Canada/Eastern, Europe/Kiev, Asia/Qatar, Singapore, Asia/Magadan, SystemV/PST8, Europe/Belfast, America/Port-au-Prince, America/St_Barthelemy, Asia/Ashgabat, Africa/Luanda, America/Nipigon, Atlantic/Jan_Mayen, Brazil/Acre, Asia/Muscat, Europe/Vilnius, Asia/Bahrain, America/Fortaleza, Etc/GMT0, US/East-Indiana, America/Hermosillo, America/Cancun, Pacific/Kosrae, Africa/Maseru, Asia/Seoul, Africa/Kinshasa, Asia/Kathmandu, Australia/Sydney, America/Lima, Australia/LHI, Europe/Madrid, America/St_Lucia, America/Bahia_Banderas, America/Montserrat, Asia/Brunei, America/Santa_Isabel, Canada/Mountain, America/Cambridge_Bay, Australia/West, Asia/Colombo, Indian/Antananarivo, US/Indiana-Starke, Australia/Brisbane, Indian/Mayotte, Asia/Urumqi, US/Aleutian, Europe/Volgograd, America/Lower_Princes, America/Vancouver, America/Rio_Branco, Africa/Blantyre, America/Detroit, America/Danmarkshavn, America/Thule, Asia/Hong_Kong, Africa/Lusaka, Iran, America/Argentina/La_Rioja, Africa/Dakar, SystemV/CST6CDT, America/Tortola, America/Porto_Velho, Asia/Sakhalin, Etc/GMT+10, America/Scoresbysund, Asia/Thimbu, Asia/Kamchatka, Africa/Harare, Etc/GMT+12, Navajo, Etc/GMT+11, NST, America/Nome, Europe/Tallinn, Turkey, Africa/Khartoum, Africa/Johannesburg, EAT, Africa/Bangui, Europe/Belgrade, Jamaica, Africa/Bissau, Asia/Tehran, WET, Europe/Astrakhan, Africa/Juba, America/Campo_Grande, America/Belem, Etc/Greenwich, Asia/Saigon, Pacific/Midway, America/Ensenada, America/Jujuy, Africa/Timbuktu, America/Virgin, America/Bahia, America/Goose_Bay, America/Pangnirtung, Asia/Katmandu, America/Phoenix, MST, Africa/Niamey, America/Whitehorse, Pacific/Noumea, Asia/Tbilisi, Europe/Kyiv, ECT, America/Montreal, Asia/Makassar, America/Argentina/San_Juan, Hongkong, UCT, Asia/Nicosia, America/Indiana/Winamac, SystemV/MST7MDT, America/Argentina/ComodRivadavia, America/Boa_Vista, Asia/Atyrau, America/Grenada, Australia/Darwin, Asia/Kuala_Lumpur, Asia/Khandyga, Asia/Thimphu, Asia/Famagusta, Asia/Rangoon, Europe/Bratislava, Asia/Calcutta, America/Argentina/Tucuman, Asia/Kabul, Indian/Cocos, Japan, Pacific/Tongatapu, Etc/GMT-12, America/New_York, Etc/GMT-11, Etc/GMT-10, America/Nuuk, SystemV/YST9YDT, Europe/Ulyanovsk, Etc/GMT-14, Etc/GMT-13, W-SU, America/Merida, EET, CAT, America/Rosario, Canada/Saskatchewan, America/St_Kitts, Arctic/Longyearbyen, America/Fort_Nelson, America/Caracas, America/Guadeloupe, Indian/Kerguelen, Asia/Hebron, SystemV/PST8PDT, Africa/Monrovia, Asia/Ust-Nera, Egypt, Asia/Srednekolymsk, America/North_Dakota/New_Salem, Asia/Anadyr, Australia/Melbourne, Asia/Irkutsk, America/Shiprock, America/Winnipeg, Europe/Vatican, Asia/Amman, Etc/UTC, SystemV/AST4ADT, Asia/Tokyo, America/Toronto, Asia/Singapore, Australia/Lindeman, America/Los_Angeles, SystemV/EST5EDT, Pacific/Majuro, America/Argentina/Buenos_Aires, Europe/Nicosia, Pacific/Guadalcanal, Europe/Athens, US/Pacific, Europe/Monaco]

        ZoneId bet = ZoneId.of("BET", ZoneId.SHORT_IDS);
        System.out.println(bet);//America/Sao_Paulo
        System.out.println("*".repeat(30));

        LocalDateTime now = LocalDateTime.now();//using America setting: 2023-09-28T02:00:28.093195300
        System.out.println(now);
        System.out.println("*".repeat(30));

        System.out.println("--- Instant class -->");
        //Instant class represent a point in time, or a timestamp.
        //Internally, it stores a value in nanoseconds, from the fixed epoch time of 1970-01-01Z

        Instant instantNow = Instant.now();
        System.out.println(instantNow); //2023-09-28T09:01:43.424167900Z //UTC with zero offset

        //using UTC as System setting:
        //2023-09-28T09:03:21.842584600
        //        ******************************
        //2023-09-28T09:03:21.842584600Z

        //list of 3 Zones with info about DaylightSaving
        for (ZoneId z : List.of(
                ZoneId.of("Australia/Sydney"),
                ZoneId.of("Europe/Paris"),
                ZoneId.of("America/New_York"))) {
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + instantNow.atZone(z).format(zoneFormat));
            System.out.println("\t" + z.getRules().getDaylightSavings(instantNow));
            System.out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }
        System.out.println("*".repeat(30));

        //we want to have a birth certificate  with the local date and time on it, for the place of birth
        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, LA time = " + dob.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))); //Your kid's birthdate, LA time = 1. 1. 2020, 0:01:00
        System.out.println("*".repeat(30));

        ZonedDateTime dobSydney = ZonedDateTime.ofInstant(dobInstant, ZoneId.of("Australia/Sydney"));
        System.out.println("Your kid's birthdate, Sydney Time = " + dobSydney.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println("*".repeat(30));

        //use a ZonedDateTime, to get another ZonedDateTime in a different time zone.
        ZonedDateTime dobHere = dobSydney.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, Here Time = " + dobHere.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println("*".repeat(30));

        System.out.println("<-- Instant class ---\n");

        System.out.println("--- TemporalAdjusters class -->");
        //when the first day of the next month is, starting with today.
        ZonedDateTime firstOfMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.printf("First of next Month = %tD %n", firstOfMonth);
        System.out.println("*".repeat(30));

        //Period
        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        System.out.println(timePast); //output: P50Y -> Period 50 Years
        System.out.println("*".repeat(30));

        //Duration
        Duration timeSince = Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince); //output: PT438288H1M -> Period time 438288 hours and 1 minute has passed
        System.out.println("*".repeat(30));

        //new kid with new dob
        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4).plusDays(4).plusHours(7).plusMinutes(14).plusSeconds(37);
        System.out.println("Your 2nd kid's birthdate, Here Time = " + dob2.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        Period timePast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        System.out.println(timePast2);
        Duration timeSince2 = Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince2);
        System.out.println("*".repeat(30));

        //between method on the ChronoUnit enum
        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(LocalDate.EPOCH)) {
                long val = u.between(LocalDate.EPOCH, dob2.toLocalDate());
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }
        System.out.println("*".repeat(30));

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(ldt)) {
                long val = u.between(ldt, dob2);
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }

        System.out.println("<-- TemporalAdjusters class ---\n");
        System.out.println(">>>----------- Time Zones ---------------<<<\n");
    }
}
