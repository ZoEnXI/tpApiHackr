package mds.tp.api.hackr.tpApi.services;

import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;
import org.xbill.DNS.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubDomainFinderService {

    public Map<String, List<String>> findAllDnsRecords(String domain) {
        Map<String, List<String>> allRecords = new HashMap<>();

        String[] recordTypes = {"A", "AAAA", "CNAME", "MX", "TXT", "NS", "SOA"};

        for (String recordType : recordTypes) {
            List<String> records = findDnsRecords(domain, recordType);
            allRecords.put(recordType, records);
        }

        return allRecords;
    }


    public List<String> findDnsRecords(String domain, String recordType) {
        List<String> recordsList = new ArrayList<>();
        try {
            int type = Type.value(recordType);
            Lookup lookup = new Lookup(domain, type);
            Record[] records = lookup.run();

            if (lookup.getResult() == Lookup.SUCCESSFUL && records != null) {
                for (Record record : records) {
                    recordsList.add(record.toString());
                }
            } else {
                System.err.println("No records found or an error occurred: " + lookup.getErrorString());
            }
        } catch (TextParseException e) {
            System.err.println("Invalid domain format: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred during DNS lookup: " + e.getMessage());
        }

        return recordsList;
    }
}
