package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.expenditureinfo.Expenditure;
import seedu.address.model.expenditureinfo.ExpenditureList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ExpenditureTracker implements ReadOnlyExpenditureTracker {

    private final ExpenditureList expenditures = new ExpenditureList();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */


    public ExpenditureTracker() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ExpenditureTracker(ReadOnlyExpenditureTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setExpenditures(List<Expenditure> expenditures) {
        this.expenditures.setExpenditures(expenditures);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyExpenditureTracker newData) {
        requireNonNull(newData);

        setExpenditures(newData.getExpenditureList());
    }

    /**
     * Returns true if an expenditure with the same identity as
     * {@code expenditure} exists in the expenditure tracker.
     */
    public boolean hasExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        return expenditures.contains(expenditure);
    }


    //// person-level operations

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addExpenditure(Expenditure e) {
        expenditures.add(e);
    }

    /**
     * Removes a person to the address book.
     */
    public void removeExpenditure(Expenditure key) {
        expenditures.remove(key);
    }

    //// util methods
    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedExpenditure}.
     * {@code target} must exist in the expenditure tracker.
     * The expenditure identity of {@code editedExpenditure}
     * must not be the same as another existing expenditure in the expenditure tracker.
     */
    public void updateExpenditure(Expenditure target, Expenditure editedExpenditure) {
        requireNonNull(editedExpenditure);
        expenditures.setExpenditures(target, editedExpenditure);
    }


    @Override
    public String toString() {
        return expenditures.asUnmodifiableObservableList().size() + " expenditures";
        // TODO: refine later
    }

    @Override
    public ObservableList<Expenditure> getExpenditureList() {
        return expenditures.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenditureTracker // instanceof handles nulls
                && expenditures.equals(((ExpenditureTracker) other).expenditures));
    }

    @Override
    public int hashCode() {
        return expenditures.hashCode();
    }
}