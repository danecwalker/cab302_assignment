//package cabbypatty.cab302_assignment.model;
//
//import cabbypatty.cab302_assignment.store.IJournalDAO;
//
//import java.util.ArrayList;
//
//public class MockJournalDAO implements IJournalDAO {
//    public static final ArrayList<Journal> journals = new ArrayList<>();
//    @Override
//    public Journal getJournal(int id) {
//        for (Journal journal : journals) {
//            if (journal.getId() == id) {
//                return journal;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Journal createJournal(String body, Integer mood, Integer author_id) {
//        return null;
//    }
//
//    @Override
//    public Journal[] getJournals(Integer author_id) {
//        return new Journal[0];
//    }
//
//    @Override
//    public void deleteJournal(int id) {
//        journals.remove(id);
//    }
//
//    @Override
//    public void updateJournal(int id, String body) {
//
//    }
//}
