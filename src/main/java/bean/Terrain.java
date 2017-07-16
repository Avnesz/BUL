package bean;

import java.util.ArrayList;
import java.util.List;

import dto.TerrainDTO;
import enums.Tuile;

public class Terrain {
	private List<List<Tuile>> sousSol = new ArrayList<>();
	private List<List<Tuile>> sol = new ArrayList<>();
	private List<List<Tuile>> layer1 = new ArrayList<>();

	/**
	 * Creer un terrain à partir d'un DTO
	 * 
	 * @param dto
	 */
	public Terrain(final TerrainDTO dto) {
		createLayer(dto.getSousSol(), sousSol);
		createLayer(dto.getSol(), sol);
		createLayer(dto.getLayer1(), layer1);
	}

	/**
	 * Creer les differentes couches du terrain
	 * 
	 * @param layerDto
	 * @param layerBean
	 */
	private void createLayer(final List<List<String>> layerDto, final List<List<Tuile>> layerBean) {
		for (final List<String> lineDto : layerDto) {
			final List<Tuile> lineBean = new ArrayList<>();
			for (final String tuileDto : lineDto) {
				lineBean.add(Tuile.getById(tuileDto));
			}
		}
	}

	/**
	 * Raffraichie le terrain avec les nouvelles données
	 * 
	 * @param newTerrain
	 */
	public void refresh(final Terrain newTerrain) {
		refreshLayer(newTerrain.getSousSol(), sousSol);
		refreshLayer(newTerrain.getSol(), sol);
		refreshLayer(newTerrain.getLayer1(), layer1);
	}

	private void refreshLayer(final List<List<Tuile>> newLayer, final List<List<Tuile>> layer) {

	}

	/**
	 * @return the sousSol
	 */
	public List<List<Tuile>> getSousSol() {
		return sousSol;
	}

	/**
	 * @param sousSol
	 *            the sousSol to set
	 */
	public void setSousSol(final List<List<Tuile>> sousSol) {
		this.sousSol = sousSol;
	}

	/**
	 * @return the sol
	 */
	public List<List<Tuile>> getSol() {
		return sol;
	}

	/**
	 * @param sol
	 *            the sol to set
	 */
	public void setSol(final List<List<Tuile>> sol) {
		this.sol = sol;
	}

	/**
	 * @return the layer1
	 */
	public List<List<Tuile>> getLayer1() {
		return layer1;
	}

	/**
	 * @param layer1
	 *            the layer1 to set
	 */
	public void setLayer1(final List<List<Tuile>> layer1) {
		this.layer1 = layer1;
	}
}
