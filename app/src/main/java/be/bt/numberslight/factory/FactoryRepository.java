package be.bt.numberslight.factory;

import be.bt.numberslight.repository.Repository;
import be.bt.numberslight.repository.RepositoryLocalData;
import be.bt.numberslight.repository.RepositoryName;
import be.bt.numberslight.repository.RepositoryRemoteData;

/**
 * I choose to write in Java all the logic for requesting data
 * <p>
 * Following my choice, I take data from different source, by the Factory Pattern
 * it is transparent to the ViewModel
 */
public class FactoryRepository {

    public static Repository getRepository(RepositoryName name) {
        Repository repository;
        switch (name) {
            case LOCALDATA:
                repository = new RepositoryLocalData();
                break;
            case REMOTEDATA:
                repository = RepositoryRemoteData.getInstance();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
        return repository;

    }
}
