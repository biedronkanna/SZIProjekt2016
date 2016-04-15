package org.dziadzi.services.impl;

import org.dziadzi.converters.LocationToDtoConverter;
import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.dziadzi.nodes.builders.LocationBuilder.aLocation;

/**
 * Created by kkuc on 2016-03-17.
 */
@RestController("locations")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationToDtoConverter converter;

	@Override
    public List<LocationDto> getLocationsRow(Integer latitude) {

		List<Location> row = locationRepository.findByLatitude(latitude);
		return row.stream().map(converter).collect(Collectors.toList());

	}

	@Override
    public void createLocations(int maxLongitude, int maxLatitude) {
		for (int latitude = 0; latitude < maxLatitude; latitude++) {
			for (int longitude = 0; longitude < maxLongitude; longitude++) {
				Location toSave = aLocation().withLongitude(longitude).withLatitude(latitude)
						.build();
				addNeighbours(toSave);
				locationRepository.save(toSave, 1);
			}

		}
	}

	private void addNeighbours(Location toSave) {
		Location left = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude() - 1,
				toSave.getLatitude());
		Location upperLeft = locationRepository
				.findByLongitudeAndLatitude(toSave.getLongitude() - 1, toSave.getLatitude() - 1);
		Location upper = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude(),
				toSave.getLatitude() - 1);
		Location upperRight = locationRepository
				.findByLongitudeAndLatitude(toSave.getLongitude() + 1, toSave.getLatitude() - 1);
		List<Location> potentialNeighbours = newArrayList(left, upperLeft, upperRight, upper);
		potentialNeighbours.stream().filter(e -> e != null)
				.forEach(e -> toSave.getNeighbours().add(e));

	}

	@Override
    @RequestMapping(method = RequestMethod.DELETE)
	public void deleteLocations() {
		locationRepository.deleteAll();
	}

	@Override
    @RequestMapping(value = "count", method = RequestMethod.GET)
	public Long count() {
		return locationRepository.count();
	}

}
