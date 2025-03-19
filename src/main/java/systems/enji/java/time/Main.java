package systems.enji.java.time;

import java.time.*;

/**
 * Demo for creating and converting between
 * <ul>
 *     <li>LocalDate</li>
 *     <li>Instant</li>
 *     <li>OffsetDateTime</li>
 *     <li>ZonedDateTime</li>
 * </ul>
 */
public class Main {

    private static final ZoneOffset ZONE_OFFSET_UTC = ZoneOffset.UTC;
    private static final ZoneOffset ZONE_OFFSET_1 = ZoneOffset.ofHours(1);
    private static final ZoneOffset ZONE_OFFSET_2 = ZoneOffset.ofHours(2);
    private static final ZoneOffset ZONE_OFFSET_7 = ZoneOffset.ofHours(7);

    private static final ZoneId ZONE_ID_VIENNA = ZoneId.of("Europe/Vienna");
    private static final ZoneId ZONE_ID_SYDNEY = ZoneId.of("Australia/Sydney");

    private static final long EPOCH_SECONDS = 1737367201L;

    private static final String DATE_STRING = "2025-01-20T10:00:01.123456789";
    private static final String DATE_STRING_Z = DATE_STRING + "Z";
    private static final String DATE_STRING_OFFSET_7 = DATE_STRING + "+07:00";
    private static final String DATE_STRING_VIENNA = String.format("%s+01:00[%s]", DATE_STRING, ZONE_ID_VIENNA);
    private static final String DATE_STRING_SYDNEY = String.format("%s+11:00[%s]", DATE_STRING, ZONE_ID_SYDNEY);

    private static final String DATE_STRING_GAP = "2025-03-30T02:30:00.123456789";
    private static final String DATE_STRING_OVERLAP = "2025-10-26T02:30:00.123456789";

    public static void main(String[] args) {

        createLocalDateTimes();
        createInstants();
        createOffsetDateTimes();
        createZonedDateTimes();

        convertToLocalDateTime();
        convertToInstant();
        convertToOffsetDateTime();
        convertToZonedDateTime();

    }

    private static void createLocalDateTimes() {

        p("\nCreating LocalDateTimes ++++++++++++");

        p(LocalDateTime.now(), "now (default)");
        p(LocalDateTime.now(ZONE_ID_VIENNA), "now (Vienna)");
        p(LocalDateTime.now(ZONE_ID_SYDNEY), "now (Sydney)");

        p(LocalDateTime.of(2025, Month.JANUARY, 20, 10, 0), "of year-to-minutes");
        p(LocalDateTime.of(2025, Month.JANUARY, 20, 10, 0, 1), "of year-to-seconds");
        p(LocalDateTime.of(2025, Month.JANUARY, 20, 10, 0, 1, 123456789), "of year-to-nanos");

        // a specific point in time represented by local date-time in the given zone offset
        p(LocalDateTime.ofEpochSecond(EPOCH_SECONDS, 123456789, ZoneOffset.UTC), "of epoch nanos (offset UTC)");
        // same point in time, but in a different zone offset:
        p(LocalDateTime.ofEpochSecond(EPOCH_SECONDS, 123456789, ZoneOffset.ofHours(1)), "of epoch nanos (offset 1)");

        p(LocalDateTime.parse(DATE_STRING), "parsed");

    }

    private static void createInstants() {

        p("\nCreating Instants ++++++++++++");

        p(Instant.now(), "now");

        p(Instant.ofEpochSecond(EPOCH_SECONDS), "of epoch seconds");
        p(Instant.ofEpochMilli(EPOCH_SECONDS * 1000 + 1), "of epoch millis");
        p(Instant.ofEpochSecond(EPOCH_SECONDS, 123456789), "of epoch nanos");

        p(Instant.parse(DATE_STRING_Z), "parsed (UTC)");
        p(Instant.parse(DATE_STRING_OFFSET_7), "parsed (offset 7)");

    }

    private static void createOffsetDateTimes() {

        p("\nCreating OffsetDateTimes ++++++++++++");

        p(OffsetDateTime.now(), "now (local default)");
        p(OffsetDateTime.now(ZONE_ID_VIENNA), "now (Vienna)");
        p(OffsetDateTime.now(ZONE_ID_SYDNEY), "now (Sydney)");

        p(OffsetDateTime.of(2025, 1, 20, 10, 0, 1, 123456789, ZONE_OFFSET_UTC), "of year-to-nanos (offset UTC)");
        p(OffsetDateTime.of(2025, 1, 20, 10, 0, 1, 123456789, ZONE_OFFSET_1), "of year-to-nanos (offset 1)");

        p(OffsetDateTime.parse(DATE_STRING_Z), "parsed (UTC)");
        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7), "parsed (offset 7)");

    }

    private static void createZonedDateTimes() {

        p("\nCreating ZonedDateTimes ++++++++++++");

        p(ZonedDateTime.now(), "now (local default)");
        p(ZonedDateTime.now(ZoneId.of("Europe/Vienna")), "now (Vienna)");
        p(ZonedDateTime.now(ZoneId.of("Australia/Sydney")), "now (Sydney)");

        p(ZonedDateTime.of(2025, 1, 20, 10, 0, 1, 123456798, ZONE_ID_VIENNA), "of year-to-nanos (Vienna)");
        p(ZonedDateTime.of(2025, 1, 20, 10, 0, 1, 123456789, ZONE_ID_SYDNEY), "of year-to-nanos (Sydney)");

        p(ZonedDateTime.parse(DATE_STRING_Z), "parsed (UTC)");
        p(ZonedDateTime.parse(DATE_STRING_OFFSET_7), "parsed (offset 7)");
        p(ZonedDateTime.parse(DATE_STRING_VIENNA), "parsed (Vienna)");
        p(ZonedDateTime.parse(DATE_STRING_SYDNEY), "parsed (Sydney)");

    }

    private static void convertToLocalDateTime() {

        p("\nConverting to LocalDateTime ++++++++++++");

        p(LocalDateTime.ofInstant(Instant.parse(DATE_STRING_Z), ZONE_ID_VIENNA), "from Instant (Vienna)");
        p(LocalDateTime.ofInstant(Instant.parse(DATE_STRING_Z), ZONE_ID_SYDNEY), "from Instant (Sydney)");

        // simply removes the "offset" part
        p(OffsetDateTime.parse(DATE_STRING_Z).toLocalDateTime(), "from ODT (UTC)");
        p(LocalDateTime.from(OffsetDateTime.parse(DATE_STRING_Z)), "from ODT (UTC)");
        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7).toLocalDateTime(), "from ODT (offset 7)");
        p(LocalDateTime.from(OffsetDateTime.parse(DATE_STRING_OFFSET_7)), "from ODT (offset 7)");

        // simply removes the "offset/zone" part
        p(ZonedDateTime.parse(DATE_STRING_Z).toLocalDateTime(), "from ZDT (UTC)");
        p(LocalDateTime.from(ZonedDateTime.parse(DATE_STRING_Z)), "from ZDT (UTC)");
        p(ZonedDateTime.parse(DATE_STRING_OFFSET_7).toLocalDateTime(), "from ZDT (offset 7)");
        p(LocalDateTime.from(ZonedDateTime.parse(DATE_STRING_OFFSET_7)), "from ZDT (offset 7)");

    }

    private static void convertToInstant() {

        p("\nConverting to Instant ++++++++++++");

        p(LocalDateTime.parse(DATE_STRING).toInstant(ZONE_OFFSET_UTC), "from LDT (UTC)");
        p(LocalDateTime.parse(DATE_STRING).toInstant(ZONE_OFFSET_7), "from LDT (offset 7)");

        p(OffsetDateTime.parse(DATE_STRING_Z).toInstant(), "from ODT (UTC)");
        p(Instant.from(OffsetDateTime.parse(DATE_STRING_Z)), "from ODT (UTC)");
        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7).toInstant(), "from ODT (offset 7)");
        p(Instant.from(OffsetDateTime.parse(DATE_STRING_OFFSET_7)), "from ODT (offset 7)");

        p(ZonedDateTime.parse(DATE_STRING_Z).toInstant(), "from ZDT (UTC)");
        p(Instant.from(ZonedDateTime.parse(DATE_STRING_Z)), "from ZDT (UTC)");
        p(ZonedDateTime.parse(DATE_STRING_OFFSET_7).toInstant(), "from ZDT (offset 7)");
        p(Instant.from(ZonedDateTime.parse(DATE_STRING_OFFSET_7)), "from ZDT (offset 7)");

    }

    private static void convertToOffsetDateTime() {

        p("\nConverting to OffsetDateTime ++++++++++++");

        // simply appends the given offset
        p(OffsetDateTime.of(LocalDateTime.parse(DATE_STRING), ZONE_OFFSET_7), "from LDT (offset 7)");
        // the following uses the previous one under the hood, but is nicer to read
        p(LocalDateTime.parse(DATE_STRING).atOffset(ZONE_OFFSET_7), "from LDT (offset 7) (nicer code)");

        p(Instant.parse(DATE_STRING_Z).atOffset(ZONE_OFFSET_7), "from instant (offset 7)");
        p(OffsetDateTime.ofInstant(Instant.parse(DATE_STRING_Z), ZONE_ID_VIENNA), "from instant (Vienna)");
        p(OffsetDateTime.ofInstant(Instant.parse(DATE_STRING_Z), ZONE_ID_SYDNEY), "from instant (Sydney)");

        p(ZonedDateTime.parse(DATE_STRING_Z).toOffsetDateTime(), "from ZDT (UTC)");
        p(OffsetDateTime.from(ZonedDateTime.parse(DATE_STRING_Z)), "from ZDT (UTC)");
        p(ZonedDateTime.parse(DATE_STRING_OFFSET_7).toOffsetDateTime(), "from ZDT (offset 7)");
        p(OffsetDateTime.from(ZonedDateTime.parse(DATE_STRING_OFFSET_7)), "from ZDT (offset 7)");
        p(ZonedDateTime.parse(DATE_STRING_VIENNA).toOffsetDateTime(), "from ZDT (Vienna)");
        p(OffsetDateTime.from(ZonedDateTime.parse(DATE_STRING_VIENNA)), "from ZDT (Vienna)");
        p(ZonedDateTime.parse(DATE_STRING_SYDNEY).toOffsetDateTime(), "from ZDT (Sydney)");
        p(OffsetDateTime.from(ZonedDateTime.parse(DATE_STRING_SYDNEY)), "from ZDT (Sydney)");

    }

    private static void convertToZonedDateTime() {

        p("\nConverting to ZonedDateTime ++++++++++++");

        // simply appends the zone (with the appropriate offset)
        p(ZonedDateTime.of(LocalDateTime.parse(DATE_STRING), ZONE_ID_VIENNA), "from LDT (Vienna)");
        // the following uses the previous one under the hood, but is nicer to read
        p(LocalDateTime.parse(DATE_STRING).atZone(ZONE_ID_VIENNA), "from LDT (Vienna) (nicer code)");

        // also providing the offset in addition to a zone ID is redundant in most cases (except for DST overlaps)
        p(ZonedDateTime.ofLocal(LocalDateTime.parse(DATE_STRING), ZONE_ID_VIENNA, ZONE_OFFSET_7), "from LDT (Vienna) (preferred offset ignored)");

        // this LDT is impossible as it is skipped due to DST
        LocalDateTime impossibleLDT = LocalDateTime.parse(DATE_STRING_GAP);
        // regardless of the specified offset, this LDT will be increased by one hour to obtain the ZDT
        p(ZonedDateTime.ofLocal(impossibleLDT, ZONE_ID_VIENNA, ZONE_OFFSET_1), "from gap LDT, with preferred offset 1 (ignored)");
        p(ZonedDateTime.ofLocal(impossibleLDT, ZONE_ID_VIENNA, ZONE_OFFSET_2), "from gap LDT, with preferred offset 2 (the only one possible)");

        // this LDT is ambiguous as it happens twice due to DST
        LocalDateTime ambiguousLDT = LocalDateTime.parse(DATE_STRING_OVERLAP);
        // by explicitly specifying one of the two possible offsets, we can decide which of the two points in time we actually mean
        p(ZonedDateTime.ofLocal(ambiguousLDT, ZONE_ID_VIENNA, ZONE_OFFSET_1), "from overlapping LDT, with preferred offset 1");
        p(ZonedDateTime.ofLocal(ambiguousLDT, ZONE_ID_VIENNA, ZONE_OFFSET_2), "from overlapping LDT, with preferred offset 2");
        p(ZonedDateTime.ofLocal(ambiguousLDT, ZONE_ID_VIENNA, ZONE_OFFSET_7), "from overlapping LDT, with preferred offset 7 (ignored)");

        p(ZonedDateTime.ofInstant(Instant.parse(DATE_STRING_Z), ZONE_ID_VIENNA), "from instant (Vienna)");
        // the following uses the previous one under the hood, but is nicer to read
        p(Instant.parse(DATE_STRING_Z).atZone(ZONE_ID_VIENNA), "from instant (Vienna) (nicer code)");

        p(ZonedDateTime.ofInstant(LocalDateTime.parse(DATE_STRING), ZONE_OFFSET_7, ZONE_ID_VIENNA), "from LDT (Vienna) (offset 7, shifts LDT)");

        try {
            p(ZonedDateTime.ofStrict(LocalDateTime.parse(DATE_STRING), ZONE_OFFSET_7, ZONE_ID_VIENNA), "from LDT (Vienna) (offset strictly enforced)");
        } catch (DateTimeException e) {
            p("offset doesn't match zone ID, which is not tolerated by 'ofStrict'");
        }

        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7).toZonedDateTime(), "from ODT (with ODT offset 7)");
        p(ZonedDateTime.from(OffsetDateTime.parse(DATE_STRING_OFFSET_7)), "from ODT (with ODT offset 7)");
        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7).atZoneSameInstant(ZONE_ID_VIENNA), "from ODT (with ODT offset 7) at Vienna (same instant)");
        p(OffsetDateTime.parse(DATE_STRING_OFFSET_7).atZoneSimilarLocal(ZONE_ID_VIENNA), "from ODT (with ODT offset 7) at Vienna (similar local)");

    }

    private static void p(Object o) {
        System.out.println(o.toString());
    }

    private static void p(Object o, String message) {
        System.out.printf("%s - %s\n", o, message);
    }

}